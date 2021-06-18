package com.mahzarasua.resumeapi.service;

import com.mahzarasua.resumeapi.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeResponse;
import com.mahzarasua.resumeapi.exception.ResumeNotFoundException;
import com.mahzarasua.resumeapi.mapper.ResumeMapper;
import com.mahzarasua.resumeapi.model.ResumeModel;
import com.mahzarasua.resumeapi.repository.ResumeRepository;
import com.mahzarasua.resumeapi.validator.ResumeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private ResumeValidator resumeValidator;

    @Override
    public List<GetResumeResponse> getAllResumes() {
        List<ResumeModel> resumes = resumeRepository.findAll();
        return resumeMapper.mapAsList(resumes, GetResumeResponse.class);
    }

    @Override
    public GetResumeResponse getResumeByResourceId(String resourceId) {
        return resumeMapper.map(resumeRepository.findById(resourceId).orElseThrow(() -> new ResumeNotFoundException(String.format("Resume with id %s was not found", resourceId))), GetResumeResponse.class);
    }

    @Override
    public ResumeResponse createResume(ResumeRequest resumeRequest) {
        validateAndSaveResume(resumeRequest);
        return new ResumeResponse(resumeRepository.findTopByOrderByCreationdateDesc().getId());
    }

    @Override
    public ResumeResponse updateResume(ResumeRequest resumeRequest, String resourceId) {
        resumeRequest.setId(resumeRepository.findById(resourceId).orElseThrow(() -> new ResumeNotFoundException(String.format("Resume with id %s was not found", resourceId))).getId());
        validateAndSaveResume(resumeRequest);
        return new ResumeResponse(resumeRequest.getId());
    }

    private void validateAndSaveResume(ResumeRequest resumeRequest) {
        resumeValidator.validate(resumeRequest);
        resumeRequest.setCreationdate((resumeRequest.getCreationdate() == null) ? new Date() : resumeRequest.getCreationdate());
        resumeRepository.save(resumeMapper.map(resumeRequest, ResumeModel.class));
    }
}
