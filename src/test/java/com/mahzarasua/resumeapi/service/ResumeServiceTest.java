package com.mahzarasua.resumeapi.service;

import com.mahzarasua.resumeapi.DummyData;
import com.mahzarasua.resumeapi.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class ResumeServiceTest {
    private ResumeRequest r = new ResumeRequest();

    @BeforeEach
    public void setUp() {
        DummyData d = new DummyData();
        r = d.setDummyData();
    }

    @Autowired
    private ResumeService resumeService;

    @Test
    public void getAllResumes() throws Exception {
        List<GetResumeResponse> response = resumeService.getAllResumes();

        System.out.println("Total of resumes: " + response.size());
        assertFalse(response.isEmpty());
    }

    @Test
    public void getResumebyId() throws Exception {
        GetResumeResponse response = resumeService.getResumeByResourceId(r.getId());

        System.out.println("Resume: " + response);
        assertEquals(response.getId(), r.getId());
    }

    @Test
    @Order(1)
    public void createResume() throws Exception{
        ResumeResponse response = resumeService.createResume(r);

        System.out.println("Resume created: " + response.getResourceId());
        assertFalse(response.getResourceId().isEmpty());
    }

    @Test
    public void createResumeNoId() throws Exception{
        r.setId(null);
        ResumeResponse response = resumeService.createResume(r);

        System.out.println("Resume created: " + response.getResourceId());
        assertFalse(response.getResourceId().isEmpty());
    }

    @Test
    public void updateResume() throws Exception{
        r.setJob("Developer");
        ResumeResponse response = resumeService.updateResume(r, r.getId());

        System.out.println("Resume updated: " + response.getResourceId());
        assertFalse(response.getResourceId().isEmpty());
    }

}
