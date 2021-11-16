package com.sparta.spartansapi.mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {
    @Id private String _id;
    private String name;
    private Integer duration;  // measured in weeks

    public Course(String _id, String name) {
        this._id = _id;
        this.name = name;

        if (name.contains("Business")) {
            duration = 5;
        } else if (name.contains("Engineering")) {
            duration = 11;
        } else {  // should throw an exception
            duration = null;
        }
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
