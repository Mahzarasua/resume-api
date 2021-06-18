package com.mahzarasua.resumeapi.controller;

import com.mahzarasua.resumeapi.AbstractTest;
import com.mahzarasua.resumeapi.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeResponse;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResumeControllerTest extends AbstractTest {
    private final String uri = "/api/v1/resume/";

    private ResumeRequest r = new ResumeRequest();

    @Override
    @Before
    public void setUp() {
        super.setUp();
        setDummyData();
    }

    @NotNull
    private void setDummyData() {
        Date from;
        try {
            from = new SimpleDateFormat("yyyy/MM/dd").parse("2012/10/21");
        } catch (ParseException e) {
            from = new Date();
        }

        r.setId("60c24aa868f6037b96817652");
        r.setFirstName("Miguel");
        r.setLastName("Hernandez");
        r.setJob("developer");
        r.setCity("colima");
        r.setCountry("Mexico");
        r.setEmail("mahzarasua@outlook.com");
        r.setPhoneNumber("+523121557091");

        List<ResumeRequest.Skill> skills = new ArrayList<>();
        List<ResumeRequest.Language> languages = new ArrayList<>();
        List<ResumeRequest.WorkExperience> workExperience = new ArrayList<>();
        List<ResumeRequest.Education> education = new ArrayList<>();
        List<ResumeRequest.Challenge> challenges = new ArrayList<>();

        ResumeRequest.SocialMedia socialMedia = new ResumeRequest.SocialMedia(null, null, null, null, null, null);
        ResumeRequest.Config config = new ResumeRequest.Config(null, "Serif", "Sans");

        skills.add(new ResumeRequest.Skill("Java", (byte) 40));
        languages.add(new ResumeRequest.Language("english", (byte) 70));
        workExperience.add(new ResumeRequest.WorkExperience("backend dev", "4th", from, null, true, "developer"));
        education.add(new ResumeRequest.Education("itlac", "isc", from, new Date(), "Bachelor"));
        challenges.add(new ResumeRequest.Challenge("rest", from, "develop a rest service", "http://google.com"));

        r.setSkills(skills);
        r.setLanguages(languages);
        r.setWorkexperience(workExperience);
        r.setEducation(education);
        r.setChallenges(challenges);
        r.setSocialMedia(socialMedia);
        r.setConfig(config);

        r.setCreationdate((r.getCreationdate() == null) ? new Date() : r.getCreationdate());
    }

    @Test
    public void createResume() throws Exception {
        r.setId(null);
        String inputJson = super.mapToJson(r);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        ResumeResponse response = super.mapFromJson(content, ResumeResponse.class);
        assertTrue(!response.getResourceId().isEmpty());
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
        for (int i = 0; i < response.length; i++)
            System.out.println("Resume found with resourceId: " + response[i].getId());
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
        assertTrue(!response.getResourceId().isEmpty());
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
        assertTrue(!response.getId().isEmpty());
        System.out.println("Resume found with resourceId: " + response.getId());
    }
}
