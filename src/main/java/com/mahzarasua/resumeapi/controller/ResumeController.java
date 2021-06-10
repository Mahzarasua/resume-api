package com.mahzarasua.resumeapi.controller;

import com.mahzarasua.resumeapi.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeResponse;
import com.mahzarasua.resumeapi.exception.ExceptionBody;
import com.mahzarasua.resumeapi.service.ResumeService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j(topic = "Controller")
@Validated
@RestController
@RequestMapping(value = "/api/v1/resume", produces = "application/json")
@Api(value = "Resume API")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves all Resumes stored in the database", notes = "This operation will return a representation of the Resume resource"
            , response = GetResumeResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Bad Request", response = ExceptionBody.class),
            @ApiResponse(code = 404, message = "Not Found", response = ExceptionBody.class),
            @ApiResponse(code = 409, message = "Conflict", response = ExceptionBody.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionBody.class),})
    public List<GetResumeResponse> getResumes() {
        log.info("Entering GET all Resumes: ");
        List<GetResumeResponse> response = resumeService.getAllResumes();
        log.info("Response: {}", response);
        return response;
    }

    @RequestMapping(value = "/{resourceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves a Resume by resource id", notes = "This operation will return a representation of the Resume resource"
            , response = GetResumeResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Bad Request", response = ExceptionBody.class),
            @ApiResponse(code = 404, message = "Not Found", response = ExceptionBody.class),
            @ApiResponse(code = 409, message = "Conflict", response = ExceptionBody.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionBody.class),})
    public GetResumeResponse getResumeByResourceId(@ApiParam(name = "resourceId", value = "resourceId", required = true) @PathVariable String resourceId) {
        log.info("Entering Get by ResourceId: {}", resourceId);
        GetResumeResponse response = resumeService.getResumeByResourceId(resourceId);
        log.info("Response: {}", response);
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Retrieves a Resume by resource id", notes = "This operation will return a representation of the Resume resource"
            , response = ResumeResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Bad Request", response = ExceptionBody.class),
            @ApiResponse(code = 404, message = "Not Found", response = ExceptionBody.class),
            @ApiResponse(code = 409, message = "Conflict", response = ExceptionBody.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionBody.class),})
    public ResumeResponse createResume(@RequestBody ResumeRequest newResume) {
        log.info("Entering Post, request: {}", newResume);
        ResumeResponse response = resumeService.createResume(newResume);
        log.info("Resume created with resourceId: {}", response);
        return response;
    }

    @RequestMapping(value = "/{resourceId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves a Resume by resource id", notes = "This operation will return a representation of the Resume resource"
            , response = ResumeResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Bad Request", response = ExceptionBody.class),
            @ApiResponse(code = 404, message = "Not Found", response = ExceptionBody.class),
            @ApiResponse(code = 409, message = "Conflict", response = ExceptionBody.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ExceptionBody.class),})
    public ResumeResponse updateResume(@ApiParam(name = "resourceId", required = true, value = "resourceId") @PathVariable String resourceId
            , @RequestBody ResumeRequest resume) {
        log.info("Entering Put, resourceId: {}, request: {}", resourceId, resume);
        ResumeResponse response = resumeService.updateResume(resume, resourceId);
        log.info("Resume created with resourceId: {}", response);
        return response;
    }
}
