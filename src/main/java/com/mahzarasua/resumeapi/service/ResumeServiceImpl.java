package com.mahzarasua.resumeapi.service;

import com.mahzarasua.resumeapi.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeResponse;
import com.mahzarasua.resumeapi.exception.ResumeNotFoundException;
import com.mahzarasua.resumeapi.mapper.ResumeMapper;
import com.mahzarasua.resumeapi.model.Resume;
import com.mahzarasua.resumeapi.repository.ResumeRepository;
import com.mahzarasua.resumeapi.validator.ResumeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<Resume> resumes = resumeRepository.findAll();
        return resumeMapper.mapAsList(resumes, GetResumeResponse.class);
    }

    @Override
    public GetResumeResponse getResumeByResourceId(String resourceId) {
        Optional<Resume> resume = resumeRepository.findById(resourceId);
        if (resume.isPresent())
            return resumeMapper.map(resume, GetResumeResponse.class);
        throw new ResumeNotFoundException(String.format("Resume with id {} was not found", resourceId));
    }

    @Override
    public ResumeResponse createResume(ResumeRequest resumeRequest) {
        resumeValidator.validate(resumeRequest);
        resumeRepository.save(resumeMapper.map(resumeRequest, Resume.class));
        return new ResumeResponse(resumeRequest.getId());
    }

    @Override
    public ResumeResponse updateResume(ResumeRequest resumeRequest, String resourceId) {
        if(resumeRepository.existsById(resourceId)){
            resumeValidator.validate(resumeRequest);
            Resume resume = resumeMapper.map(resumeRequest, Resume.class);
            resumeRequest.setId(resourceId);
            resumeRepository.save(resume);
            return new ResumeResponse(resumeRequest.getId());
        }
        throw new ResumeNotFoundException(String.format("Resume with id {} was not found", resourceId));
    }
}
