package com.sparta.spartansapi.mongodb.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.Date;

@Document(collection = "spartans")
public class Spartan {
    @Id
    private ObjectId id;
    @TextIndexed private String firstName;
    @TextIndexed private String middleName;
    @TextIndexed private String lastName;
    private Date startDate;
    private Date endDate;
    private Course course;
    private Stream stream;
    private String email;
    @TextScore Float score;

    public Spartan() { }

    public Spartan(String id, String firstName, String middleName, String lastName, Date startDate,
                   Course course, Stream stream, String email, Date endDate) {
        this.id = new ObjectId(id);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.course = course;
        this.stream = stream;
        this.email = email;
        this.endDate = endDate;
    }

    public Spartan(String firstName, String middleName, String lastName, Date startDate,
                   Course course, Stream stream, String email, Date endDate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.course = course;
        this.stream = stream;
        this.email = email;
        this.endDate = endDate;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

