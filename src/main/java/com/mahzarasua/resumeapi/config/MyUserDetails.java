package com.mahzarasua.resumeapi.config;

import com.mahzarasua.resumeapi.model.User.*;
import com.mahzarasua.resumeapi.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorityList;

    public MyUserDetails(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorityList = Arrays.stream(getRoles(user).split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @NotNull
    private String getRoles(User user) {
        StringBuilder builder = new StringBuilder();
        for (Roles role: user.getRoles()
             ) {
            builder.append(role.getRole() + ",");
        }
        String roles = builder.toString();
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
