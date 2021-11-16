package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {

    List<Course> getCoursesByNameContains(String course);

}
