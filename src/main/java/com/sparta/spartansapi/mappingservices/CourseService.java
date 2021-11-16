package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.mongodb.models.Course;
import com.sparta.spartansapi.mongodb.repos.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;


@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public ResponseEntity<HttpStatus> deleteById(String id) {
        try {
            courseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Course>> getByCourseName(String name) {
        try {
            List<Course> courses = courseRepository.getCoursesByNameContains(name);
            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Course> updateCourse(String id, Course courseParam){
        Optional<Course> courseData = courseRepository.findById(id);
        if (courseData.isPresent()){
            Course course = courseData.get();
            course.setName(courseParam.getName());
            return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Course> addCourse(Course course) {
       try {
           return new ResponseEntity<>(courseRepository.insert(course), HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
