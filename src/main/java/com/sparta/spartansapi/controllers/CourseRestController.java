package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.mongodb.repos.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

//    @Autowired
//    private CourseMappingService courseMappingService;

    private CourseRepository courseRepository;

    @Autowired
    public CourseRestController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

}
