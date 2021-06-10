package com.mahzarasua.resumeapi.repository;

import com.mahzarasua.resumeapi.model.ResumeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends MongoRepository<ResumeModel, String> {
    ResumeModel findTopByOrderByCreationdateDesc();
}
