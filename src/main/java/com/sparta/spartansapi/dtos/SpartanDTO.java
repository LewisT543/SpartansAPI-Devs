package com.sparta.spartansapi.dtos;

import com.sparta.spartansapi.utils.Utilities;

import java.util.Date;

public class SpartanDTO {
    private String firstName, lastName;
    private String middleName, course, stream, email;
    private Date startDate, endDate;

    public SpartanDTO(String firstName, String lastName, String middleName, String course,
                      String stream, String email, Date startDate, Date endDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.course = course;
        this.stream = stream;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public String getStartDate() {
        return Utilities.dateToString(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return Utilities.dateToString(endDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
