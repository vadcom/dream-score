package io.poleray.api;

import io.poleray.model.Score;
import io.poleray.model.Section;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import io.poleray.dao.ScoreDAO;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-11-17T13:56:19.196670531Z[GMT]")
@RestController
public class AppApiController implements AppApi {

    private static final Logger log = LoggerFactory.getLogger(AppApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ScoreDAO scoreDAO;

    @org.springframework.beans.factory.annotation.Autowired
    public AppApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Score>> appSectionGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("app") String app,
                                                     @Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("section") String section,
                                                     @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "100" }, defaultValue="0")) @Valid @RequestParam(value = "positionToSkip", required = false, defaultValue="0") Integer positionToSkip,
                                                     @Max(100) @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "100" }, maximum="100", defaultValue="10")) @Valid @RequestParam(value = "count", required = false, defaultValue="10") Integer count,
                                                     @Parameter(in = ParameterIn.QUERY, description = "Device Identificator for local score", name = "deviceId")  String deviceId)
    {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Score>>(scoreDAO.getScoreBySection(app, section, positionToSkip, count, deviceId), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Score>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<List<Score>>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Get score around record with id.
     * Get upper and lower scores around record with id.
     * @param app
     * @param section
     * @param id record id
     * @param count number of records to return
     * @return list of scores
     */
    public ResponseEntity<List<Score>> getRecordsAroundId(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("app") String app,
                                                          @Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("section") String section,
                                                          @Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema(allowableValues={ "123456" })) @PathVariable("id") String id,
                                                          @Max(100) @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "count", required = false, defaultValue="10") Integer count,
                                                          @Parameter(in = ParameterIn.QUERY, description = "Device Identificator for local score", name = "deviceId", required=false)  String deviceId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Score>>(scoreDAO.getScoreById(app, section, id, count, deviceId), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Score>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<List<Score>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Score>> appSectionUserGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("app") String app,
                                                         @Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("section") String section,
                                                         @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "user", required = false) String user,
                                                         @Parameter(in = ParameterIn.QUERY, description = "Get records after this ID" ,schema=@Schema()) @Valid @RequestParam(value = "id", required = false) String id,
                                                         @Max(100) @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "100" }, maximum="100")) @Valid @RequestParam(value = "count", required = false) Integer count,
                                                         @Parameter(in = ParameterIn.QUERY, description = "Device Identificator for local score", name = "deviceId")  String deviceId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Score>>(scoreDAO.getUserScore(app,section,user,id,count,deviceId), HttpStatus.OK);
            } catch (Exception e) {
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
                return new ResponseEntity<List<Section>>(scoreDAO.getAppSections(app), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Section>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<List<Section>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Score>> newScore(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("app") String app,
                                                @Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("section") String section,
                                                @Parameter(in = ParameterIn.DEFAULT, description = "Create a new score in the store", required=true, schema=@Schema()) @Valid @RequestBody Score body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                String id = scoreDAO.addScore(app, section, body);
                return new ResponseEntity<List<Score>>(scoreDAO.getScoreById(app, section, id, 10, body.getDeviceId()), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Score>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Score>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
