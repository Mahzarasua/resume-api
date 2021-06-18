package com.mahzarasua.resumeapi.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("GetResumeResponse")
public class GetResumeResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String job;
    private String city;
    private String country;
    private String email;
    private String phoneNumber;
    private List<Skill> skills;
    private List<Language> languages;
    private List<WorkExperience> workexperience;
    private List<Education> education;
    private List<Challenge> challenges;
    private SocialMedia socialMedia;
    private Config config;
    private Date creationdate;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Challenge {
        private String name;
        private Date date;
        private String description;
        private String url;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Config {
        private String color;
        private String fontFamily;
        private String genericFamily;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Education {
        private String schoolName;
        private String career;
        private Date from;
        private Date to;
        private String degree;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Language {
        private String name;
        private byte percentage;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Skill {
        private String name;
        private byte percentage;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class SocialMedia {
        private String facebook;
        private String instagram;
        private String snapchat;
        private String pinterest;
        private String twitter;
        private String linkedin;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class WorkExperience {
        private String title;
        private String company;
        private Date from;
        private Date to;
        private Boolean current;
        private String description;
    }
}
