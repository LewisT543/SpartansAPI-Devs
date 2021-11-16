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

    public ResponseEntity<HttpStatus> deleteById(String id) {
        try {
            courseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Course> getByCourseName(String name) {
        return courseRepository.getCoursesByCourseNameContains(name);
    }

    public ResponseEntity<Course> updateStream(String id, Course courseParam){
        Optional<Course> courseData = courseRepository.findById(id);
        if (courseData.isPresent()){
            Course course = courseData.get();
            course.setCourseName(courseParam.getCourseName());
            course.setCourseDuration(courseParam.getCourseDuration());
            return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
