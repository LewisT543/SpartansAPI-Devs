package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
