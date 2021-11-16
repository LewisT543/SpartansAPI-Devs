package com.sparta.spartansapi.mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "spartans")
public class Spartan {
    @Id
    private String id;
    @TextIndexed private String firstName;
    @TextIndexed private String middleName;
    @TextIndexed private String lastName;
    private Date startDate;
    private Date endDate;
    private String course;
    private String stream;
    private String email;

    public Spartan(String firstName, String middle_name, String last_name, Date start_date,
                   String course, String stream, String email, Date end_date) {
        this.firstName = firstName;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.startDate = start_date;
        this.course = course;
        this.stream = stream;
        this.email = email;
        this.endDate = end_date;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}