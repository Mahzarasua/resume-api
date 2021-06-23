package com.mahzarasua.resumeapi;

import com.mahzarasua.resumeapi.domain.ResumeRequest;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyData {

    @NotNull
    public ResumeRequest setDummyData() {
        ResumeRequest r = new ResumeRequest();
        Date from;
        from = getDate();

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
        return r;
    }

    private Date getDate() {
        Date from;
        try {
            from = new SimpleDateFormat("yyyy/MM/dd").parse("2012/10/21");
        } catch (ParseException e) {
            from = new Date();
        }
        return from;
    }
}
