package com.mahzarasua.resumeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "resumes")
@Data
public class ResumeModel {
    @Id
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
    @Getter
    @Setter
    public static class Challenge {
        private final String name;
        private final Date date;
        private final String description;
        private final String url;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Config {
        private final String color;
        private final String fontFamily;
        private final String genericFamily;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Education {
        private final String schoolName;
        private final String career;
        private final Date from;
        private final Date to;
        private final String degree;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Language {
        private final String name;
        private final byte percentage;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Skill {
        private final String name;
        private final byte percentage;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class SocialMedia {
        private final String facebook;
        private final String instagram;
        private final String snapchat;
        private final String pinterest;
        private final String twitter;
        private final String linkedin;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class WorkExperience {
        private final String title;
        private final String company;
        private final Date from;
        private final Date to;
        private final Boolean current;
        private final String description;
    }
}
