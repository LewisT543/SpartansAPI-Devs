package com.sparta.spartansapi.dtos;

import com.sparta.spartansapi.utils.Utilities;

import java.util.Date;

public class SpartanDTO {
    private String first_name, last_name;
    private String middle_name, course, stream, email;
    private Date start_date, end_date;

    public SpartanDTO(String first_name, String last_name, String middle_name, String course,
                      String stream, String email, Date start_date, Date end_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.course = course;
        this.stream = stream;
        this.email = email;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public SpartanDTO() {

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
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

    public String getStart_date() {
        return Utilities.dateToString(start_date);
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return Utilities.dateToString(end_date);
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
