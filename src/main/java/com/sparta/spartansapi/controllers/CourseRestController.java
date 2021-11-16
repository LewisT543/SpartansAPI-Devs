package com.sparta.spartansapi.controllers;


import com.sparta.spartansapi.mappingservices.CourseService;
import com.sparta.spartansapi.mongodb.models.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Course> getAllCourses() {
        return null;
    }

    @GetMapping(params = {"coursename"})
    public List<Course> getAllCoursesByName(@RequestParam String coursename) {
        return courseService.getByCourseName(coursename);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable("id") String id) {
        return courseService.deleteById(id);
    }
}
