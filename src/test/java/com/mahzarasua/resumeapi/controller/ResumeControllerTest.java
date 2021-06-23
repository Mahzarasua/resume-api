package com.mahzarasua.resumeapi.controller;

import com.mahzarasua.resumeapi.AbstractTest;
import com.mahzarasua.resumeapi.DummyData;
import com.mahzarasua.resumeapi.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@WebAppConfiguration
public class ResumeControllerTest extends AbstractTest {
    private final String uri = "/api/v1/resume/";

    private ResumeRequest r = new ResumeRequest();

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        DummyData d = new DummyData();
        r = d.setDummyData();
    }

    @Test
    @Order(1)
    public void createResume() throws Exception {
        String inputJson = super.mapToJson(r);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        ResumeResponse response = super.mapFromJson(content, ResumeResponse.class);
        assertFalse(response.getResourceId().isEmpty());
        System.out.println("Resume created with resourceId: " + response.getResourceId());
    }

    @Test
    public void createResumeNoId() throws Exception {
        r.setId(null);
        String inputJson = super.mapToJson(r);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        ResumeResponse response = super.mapFromJson(content, ResumeResponse.class);
        assertFalse(response.getResourceId().isEmpty());
        System.out.println("Resume created with resourceId: " + response.getResourceId());
    }

    @Test
    public void getResumes() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        GetResumeResponse[] response = super.mapFromJson(content, GetResumeResponse[].class);
        assertTrue(response.length > 0);
        System.out.println("Total of resumes: " + response.length);
        for (GetResumeResponse getResumeResponse : response)
            System.out.println("Resume found with resourceId: " + getResumeResponse.getId());
    }

    @Test
    public void updateResume() throws Exception {
        r.setJob("Developer");
        String inputJson = super.mapToJson(r);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri + r.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ResumeResponse response = super.mapFromJson(content, ResumeResponse.class);
        assertFalse(response.getResourceId().isEmpty());
        System.out.println("Resume updated with resourceId: " + response.getResourceId());
    }

    @Test
    public void getResumebyId() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + r.getId())
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        GetResumeResponse response = super.mapFromJson(content, GetResumeResponse.class);
        assertFalse(response.getId().isEmpty());
        System.out.println("Resume found with resourceId: " + response.getId());
    }
}
