package com.sparta.spartansapi.mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@Document(collection = "spartans")
public class Spartan {
    @Id private String id;
    @TextIndexed(weight=2) private String firstName;
    @TextIndexed(weight=1) private String middleName;
    @TextIndexed(weight=3) private String lastName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Course course;
    private String stream;
    private String email;

    public Spartan(String firstName, String middleName, String lastName, LocalDate startDate, Course course, String stream, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.course = course;
        this.stream = stream;
        this.email = email;
        setEndDate();
    }

    private void setEndDate() {
        if (course.getName().toLowerCase(Locale.ROOT).contains("engineering"))
            this.endDate = this.getStartDate().plus(8, ChronoUnit.WEEKS).plus(2, ChronoUnit.YEARS);
        else
            this.endDate = this.getStartDate().plus(5, ChronoUnit.WEEKS).plus(2, ChronoUnit.YEARS);
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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

