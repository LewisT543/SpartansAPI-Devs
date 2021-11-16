package com.sparta.spartansapi.mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "courses")
public class Course {
    @Id private String _id;
    @Field(value = "course_name")
    private String courseName;
    private Integer course_duration;

    public Course(String courseName) {
        this.courseName = courseName;
        if (courseName.contains("Business")) {
            course_duration = 5;
        } else if (courseName.contains("Engineering")) {
            course_duration = 11;
        } else {
            course_duration = null;
        }
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourse_duration() {
        return course_duration;
    }

    public void setCourse_duration(Integer course_duration) {
        this.course_duration = course_duration;
    }
}
