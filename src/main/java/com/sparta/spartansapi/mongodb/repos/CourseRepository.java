package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {

    List<Course> getCoursesByNameContains(String course);

}
