package com.mahzarasua.resumeapi.repository;

import com.mahzarasua.resumeapi.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    Optional<User> findByUsername(String username);
}
