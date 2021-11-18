package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.mongodb.models.Course;
import com.sparta.spartansapi.mongodb.repos.CourseRepository;
import com.sparta.spartansapi.utils.APIMessageResponse;
import com.sparta.spartansapi.utils.APIResponse;
import com.sparta.spartansapi.utils.ResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
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
            return new ResponseEntity<>(new APIResponse(new ArrayList<>(List.of(courseRepository.insert(course))), ResponseManager.RECORD_ADDED, 1, HttpStatus.CREATED.value()), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllCourses() {
        try {
            List<Course> courses = courseRepository.findAll();
            if (courses.isEmpty()) {
                return new ResponseEntity<>(new APIResponse(courses,ResponseManager.NO_RECORDS_FOUND,0, HttpStatus.OK.value()), HttpStatus.OK);
            } else
                return new ResponseEntity<>(new APIResponse(courses,ResponseManager.RECORDS_FOUND,courses.size(),HttpStatus.OK.value()),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getByCourseName(String name) {
        try {
            List<Course> courses = courseRepository.getCoursesByNameContains(name);
            if (courses.isEmpty()){
                return new ResponseEntity<>(new APIResponse( courses,ResponseManager.NO_RECORDS_FOUND,0, HttpStatus.OK.value()), HttpStatus.OK);
            } else
                return new ResponseEntity<>(new APIResponse(courses,ResponseManager.RECORDS_FOUND,courses.size(),HttpStatus.OK.value()),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateCourse(String id, Course courseParam) {
        try {
            Optional<Course> courseData = courseRepository.findById(id);
            if (courseData.isPresent()) {
                Course course = courseData.get();
                course.setName(courseParam.getName());
                return new ResponseEntity<>(new APIResponse(new ArrayList<>(List.of(courseRepository.save(course))),ResponseManager.RECORD_UPDATED,1,HttpStatus.OK.value()),HttpStatus.OK);
            } else
                return new ResponseEntity<>(new APIMessageResponse(ResponseManager.NO_RECORD_FOUND), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteById(String id) {
        try {
            Optional<Course> foundCourse = courseRepository.findById(id);
            if (foundCourse.isPresent()) {
                courseRepository.deleteById(id);
                return new ResponseEntity<>(new APIMessageResponse(ResponseManager.RECORD_DELETED), HttpStatus.OK);
            } else
                return new ResponseEntity<>(new APIMessageResponse(ResponseManager.NO_RECORD_FOUND), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
