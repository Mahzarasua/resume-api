package com.mahzarasua.resumeapi.controller;

import com.mahzarasua.resumeapi.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeResponse;
import com.mahzarasua.resumeapi.exception.InvalidFormatException;
import com.mahzarasua.resumeapi.exception.MissingRequiredFieldException;
import com.mahzarasua.resumeapi.exception.ResumeNotFoundException;
import com.mahzarasua.resumeapi.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetResumeResponse> getResumes(){
        return resumeService.getAllResumes();
    }

    @GetMapping("/{resourceId}")
    @ResponseStatus(HttpStatus.OK)
    public GetResumeResponse getResumeByResourceId(@PathVariable String resourceId){
        try {
            return resumeService.getResumeByResourceId(resourceId);
        } catch (ResumeNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResumeResponse createResume(@RequestBody ResumeRequest newResume){
        try{
            return resumeService.createResume(newResume);
        } catch (MissingRequiredFieldException | InvalidFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{resourceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResumeResponse updateResume(@RequestBody ResumeRequest resume, @PathVariable String resourceId){
        try{
            return resumeService.updateResume(resume, resourceId);
        } catch (ResumeNotFoundException e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (MissingRequiredFieldException | InvalidFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
