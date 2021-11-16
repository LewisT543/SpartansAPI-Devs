package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {

    List<Course> getCoursesByCourseNameContains(String name);

    //List<Course> findAll();
    //List<Course> findCourseByName(String name);
    //void addCourse(Course course); // i don't think we need this either
 //   void deleteCourseById(String id);  // i don't think we need this
    //void updateCourseById(String id); //I thought we did to implement with the built in
    // repository already contains deleteById() method so deleteCourseById() is redundant
    // here are all the repo methods (incoming):
    // count, delete, deleteAll, deleteAllById, deleteById, existsById, exists, findAll, findAllById, findById, findOne
    // insert, save, saveAll
    //you right
}
