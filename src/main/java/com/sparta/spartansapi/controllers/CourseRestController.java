package com.sparta.spartansapi.controllers;


import com.sparta.spartansapi.mappingservices.CourseService;
import com.sparta.spartansapi.mongodb.models.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

    private CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping(params = {"coursename"})
    public ResponseEntity<List<Course>> getAllCoursesByName(@RequestParam String coursename) {
        return courseService.getByCourseName(coursename);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable("id") String id) {
        return courseService.deleteById(id);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Course> updateCourseById(@PathVariable("id") String id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }
}
