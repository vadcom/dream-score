package io.poleray.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(1)
public class TokenAuthFilter implements Filter {

    @Autowired
    private TokenProperties tokenProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        // Strip context path to get the relative path
        String contextPath = req.getContextPath();
        String relativePath = path.substring(contextPath.length());

        // Skip auth for non-app endpoints (swagger-ui, api-docs, root)
        if (relativePath.isEmpty() || relativePath.equals("/")
                || relativePath.startsWith("/swagger") || relativePath.startsWith("/api-docs")
                || relativePath.startsWith("/webjars")) {
            chain.doFilter(request, response);
            return;
        }

        // Extract app name — first path segment: /{app}/...
        String[] segments = relativePath.split("/");
        if (segments.length < 2) {
            chain.doFilter(request, response);
            return;
        }
        String app = segments[1];

        Map<String, String> tokens = tokenProperties.getTokens();
        if (tokens == null || tokens.isEmpty()) {
            // No tokens configured — allow all
            chain.doFilter(request, response);
            return;
        }

        String expectedToken = tokens.get(app);
        if (expectedToken == null) {
            // No token configured for this app — reject
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Unknown application");
            return;
        }

        String providedToken = req.getHeader("X-Api-Token");
        if (expectedToken.equals(providedToken)) {
            chain.doFilter(request, response);
        } else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        }
    }

    @Configuration
    @ConfigurationProperties(prefix = "app")
    public static class TokenProperties {
        private Map<String, String> tokens = new HashMap<>();

        public Map<String, String> getTokens() {
            return tokens;
        }

        public void setTokens(Map<String, String> tokens) {
            this.tokens = tokens;
        }
    }
}
