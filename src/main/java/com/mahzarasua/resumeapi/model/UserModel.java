package com.mahzarasua.resumeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
public class UserModel {
    @Id
    private String id;
    private String username;
    private String password;
    private boolean active;
    private List<Roles> roles;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Roles {
        private final String role;
    }
}
