package io.poleray.api;

import io.poleray.model.Score;
import io.poleray.model.Section;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-11-17T13:56:19.196670531Z[GMT]")
@RestController
public class AppApiController implements AppApi {

    private static final Logger log = LoggerFactory.getLogger(AppApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AppApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Score>> appSectionGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("app") String app, @Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("section") String section, @Max(100) @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "100" }, maximum="100"
, defaultValue="1")) @Valid @RequestParam(value = "position", required = false, defaultValue="1") Integer position, @Max(100) @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "100" }, maximum="100"
, defaultValue="10")) @Valid @RequestParam(value = "count", required = false, defaultValue="10") Integer count) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Score>>(objectMapper.readValue("[ {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"score\" : 10,\n  \"subscriotin\" : \"SDFRTSDERERFGKE\",\n  \"name\" : \"Jon\",\n  \"id\" : 256,\n  \"position\" : 3\n}, {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"score\" : 10,\n  \"subscriotin\" : \"SDFRTSDERERFGKE\",\n  \"name\" : \"Jon\",\n  \"id\" : 256,\n  \"position\" : 3\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Score>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Score>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Score>> appSectionIdIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("app") String app,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("section") String section, @Max(100) @Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema(allowableValues={ "100" }, maximum="100"
)) @PathVariable("id") Integer id, @Max(100) @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "100" }, maximum="100"
, defaultValue="10")) @Valid @RequestParam(value = "count", required = false, defaultValue="10") Integer count) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Score>>(objectMapper.readValue("[ {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"score\" : 10,\n  \"subscriotin\" : \"SDFRTSDERERFGKE\",\n  \"name\" : \"Jon\",\n  \"id\" : 256,\n  \"position\" : 3\n}, {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"score\" : 10,\n  \"subscriotin\" : \"SDFRTSDERERFGKE\",\n  \"name\" : \"Jon\",\n  \"id\" : 256,\n  \"position\" : 3\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Score>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Score>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Score>> appSectionUserGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("app") String app,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("section") String section,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "user", required = false) String user, @Max(100) @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "100" }, maximum="100"
)) @Valid @RequestParam(value = "count", required = false) Integer count) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Score>>(objectMapper.readValue("[ {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"score\" : 10,\n  \"subscriotin\" : \"SDFRTSDERERFGKE\",\n  \"name\" : \"Jon\",\n  \"id\" : 256,\n  \"position\" : 3\n}, {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"score\" : 10,\n  \"subscriotin\" : \"SDFRTSDERERFGKE\",\n  \"name\" : \"Jon\",\n  \"id\" : 256,\n  \"position\" : 3\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Score>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Score>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Section>> appSectionsGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("app") String app) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Section>>(objectMapper.readValue("[ {\n  \"name\" : \"English with basic set\",\n  \"id\" : \"english-easy\"\n}, {\n  \"name\" : \"English with basic set\",\n  \"id\" : \"english-easy\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Section>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Section>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Score>> newScore(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("app") String app,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("section") String section,@Parameter(in = ParameterIn.DEFAULT, description = "Create a new score in the store", required=true, schema=@Schema()) @Valid @RequestBody Score body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Score>>(objectMapper.readValue("[ {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"score\" : 10,\n  \"subscriotin\" : \"SDFRTSDERERFGKE\",\n  \"name\" : \"Jon\",\n  \"id\" : 256,\n  \"position\" : 3\n}, {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"score\" : 10,\n  \"subscriotin\" : \"SDFRTSDERERFGKE\",\n  \"name\" : \"Jon\",\n  \"id\" : 256,\n  \"position\" : 3\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Score>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Score>>(HttpStatus.NOT_IMPLEMENTED);
    }

}