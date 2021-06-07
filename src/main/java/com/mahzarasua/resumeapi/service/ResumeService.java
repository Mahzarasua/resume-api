package com.mahzarasua.resumeapi.service;

import com.mahzarasua.resumeapi.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeRequest;

import java.util.List;

public interface ResumeService {
    List<GetResumeResponse> getAllResumes();
    GetResumeResponse getResumeByResourceId(String resourceId);
    ResumeResponse createResume(ResumeRequest resumeRequest);
    ResumeResponse updateResume(ResumeRequest resumeRequest, String resourceId);
}
