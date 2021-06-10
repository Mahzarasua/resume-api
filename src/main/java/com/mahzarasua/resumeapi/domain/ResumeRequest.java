package com.mahzarasua.resumeapi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("ResumeRequest")
public class ResumeRequest {

    @ApiModelProperty(dataType = "string", value = "resourceId")
    private String id;

    @ApiModelProperty(dataType = "string", value = "firstName", required = true)
    @NotBlank
    private String firstName;

    @ApiModelProperty(dataType = "string", value = "lastName", required = true)
    @NotBlank
    private String lastName;

    @ApiModelProperty(dataType = "string", value = "job", required = true)
    @NotBlank
    private String job;

    @ApiModelProperty(dataType = "string", value = "city", required = true)
    @NotBlank
    private String city;

    @ApiModelProperty(dataType = "string", value = "country", required = true)
    @NotBlank
    private String country;

    @ApiModelProperty(dataType = "string", value = "email", required = true)
    @NotBlank
    @Email(regexp = "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$")
    private String email;

    @ApiModelProperty(dataType = "string", value = "phoneNumber",required = true)
    @NotBlank
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
        @ApiModelProperty(required = true)
        private final String name;

        @ApiModelProperty(required = true)
        private final Date date;

        @ApiModelProperty(required = true)
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
        @ApiModelProperty(required = true)
        private final String schoolName;

        @ApiModelProperty(required = true)
        private final String career;

        @ApiModelProperty(required = true)
        private final Date from;

        @ApiModelProperty(required = true)
        private final Date to;

        @ApiModelProperty(required = true)
        private final String degree;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Language {
        @ApiModelProperty(required = true)
        @NotBlank
        private final String name;

        @ApiModelProperty(required = true)
        @NotBlank
        private final byte percentage;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Skill {
        @ApiModelProperty(dataType = "string", name = "name", required = true)
        @NotBlank
        private final String name;

        @ApiModelProperty(dataType = "byte", name = "percentage", required = true)
        @NotBlank
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
