package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.mongodb.models.Course;
import com.sparta.spartansapi.mongodb.repos.CourseRepository;
import com.sparta.spartansapi.response_entities.ErrorCodes;
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

    public ResponseEntity<?> deleteById(String id) {
        try {
            if (courseRepository.existsById(id)) {
                courseRepository.deleteById(id);
                return ErrorCodes.RECORD_DELETED;
            } else return ErrorCodes.NO_MATCHES_FOUND;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getByCourseName(String name) {
        try {
            List<Course> courses = courseRepository.getCoursesByNameContains(name);
            if (courses.isEmpty())
                return ErrorCodes.NO_MATCHES_FOUND;
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> updateCourse(String id, Course courseParam){
        Optional<Course> courseData = courseRepository.findById(id);
        if (courseData.isPresent()) {
            Course course = courseData.get();
            course.setName(courseParam.getName());
            courseRepository.save(course);
            return ErrorCodes.RECORD_UPDATED;
        } else return ErrorCodes.NO_RECORD_FOUND;
    }


    public ResponseEntity<?> addCourse(Course course) {
       try {
           courseRepository.insert(course);
           return ErrorCodes.NEW_RECORD;
       } catch (Exception e) {
           e.printStackTrace();
           return ErrorCodes.INTERNAL_SERVER_ERROR;
       }
    }

    public ResponseEntity<?> getAllCourses() {
        try {
            List<Course> courses = courseRepository.findAll();
            if (!courses.isEmpty()) {
                return ErrorCodes.NO_RECORDS_FOUND;
            } else return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }


}
