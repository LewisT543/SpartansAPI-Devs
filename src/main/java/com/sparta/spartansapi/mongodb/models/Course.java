package com.sparta.spartansapi.mongodb.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {
    @Id private String id;
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
}
