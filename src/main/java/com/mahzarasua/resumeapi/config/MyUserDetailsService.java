package com.mahzarasua.resumeapi.config;

import com.mahzarasua.resumeapi.mapper.ResumeMapper;
import com.mahzarasua.resumeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ResumeMapper resumeMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return resumeMapper.map(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Not found: " + username)), MyUserDetails.class);
    }
}
