package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.mappingservices.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseRestController {
    @Autowired
    private CourseService courseService;

    @DeleteMapping(value = "/delete/{id}", params = {"id"})
    public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable("id") String id) {
        return courseService.deleteById(id);
    }
}
