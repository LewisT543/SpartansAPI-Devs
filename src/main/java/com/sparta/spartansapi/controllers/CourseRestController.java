package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.mappingservices.CourseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

    @Autowired
    private CourseMappingService courseMappingService;
}
