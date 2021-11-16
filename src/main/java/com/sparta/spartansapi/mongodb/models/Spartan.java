package com.sparta.spartansapi.mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "spartans")
public class Spartan {
    @Id private String _id;
    @Indexed private String first_name;
    @Indexed private String middle_name;
    @Indexed private String last_name;
    private Date start_date;
    private Date end_date;
    private String course;
    private String stream;
    private String email;

    public Spartan(String first_name, String middle_name, String last_name, Date start_date,
                   String course, String stream, String email, Date end_date) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.start_date = start_date;
        this.course = course;
        this.stream = stream;
        this.email = email;
        this.end_date = end_date;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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

