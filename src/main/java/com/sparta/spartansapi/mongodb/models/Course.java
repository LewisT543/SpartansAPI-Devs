package com.sparta.spartansapi.mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "courses")
public class Course {
    @Id private String _id;
    @Field(value = "course_name")
    private String courseName;
    @Field(value = "course_duration")
    private Integer courseDuration;  // measured in weeks

    public Course(String courseName) {
        this.courseName = courseName;
        if (courseName.contains("Business")) {
            courseDuration = 5;
        } else if (courseName.contains("Engineering")) {
            courseDuration = 11;
        } else {
            courseDuration = null;
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

    public Integer getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(Integer courseDuration) {
        this.courseDuration = courseDuration;
    }
}
