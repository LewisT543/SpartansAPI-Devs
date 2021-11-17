package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.mongodb.models.Course;
import com.sparta.spartansapi.mongodb.repos.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;


@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public ResponseEntity<?> addCourse(Course course) {
        try {
            return new ResponseEntity<>(courseRepository.insert(course), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllCourses() {
        try {
            List<Course> courses = courseRepository.findAll();
            if (courses.isEmpty()) {
                return new ResponseEntity<>("No Courses Found", HttpStatus.OK);
            } else
                return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getByCourseName(String name) {
        try {
            List<Course> courses = courseRepository.getCoursesByNameContains(name);
            if (courses.isEmpty()) {
                return new ResponseEntity<>("Cannot Get Course By Name: " + name, HttpStatus.OK);
            } else
                return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateCourse(String id, Course courseParam) {
        try {
            Optional<Course> courseData = courseRepository.findById(id);
            if (courseData.isPresent()) {
                Course course = courseData.get();
                course.setName(courseParam.getName());
                return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
            } else
                return new ResponseEntity<>("Cannot update Course with id:" + id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteById(String id) {
        try {
            Optional<Course> foundCourse = courseRepository.findById(id);
            if (foundCourse.isPresent()) {
                courseRepository.deleteById(id);
                return new ResponseEntity<>("Course Deleted with id: " + id, HttpStatus.OK);
            } else
                return new ResponseEntity<>("Cannot Delete Course with id: " + id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
