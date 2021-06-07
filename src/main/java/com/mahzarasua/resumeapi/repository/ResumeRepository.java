package com.mahzarasua.resumeapi.repository;

import com.mahzarasua.resumeapi.model.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, String> {
}
