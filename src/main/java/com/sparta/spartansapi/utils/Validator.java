package com.sparta.spartansapi.utils;

import com.sparta.spartansapi.mongodb.models.Course;
import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.mongodb.models.Stream;

import java.util.Date;

public interface Validator {

    boolean isSpartanValid(Spartan spartan);
    boolean isCourseValid(Course course);
    boolean isStreamValid(Stream stream);

    // spartan data
    boolean isFirstNameNotNull(String firstName);
    boolean isLastNameNotNull(String lastName);
    boolean isStartDateNotNull(String startDate);
    boolean isCourseNotNull(Course course);
    boolean isStreamNotNull(Stream stream);
    boolean isEmailNotNull(String email);
    boolean isEndDateNotNull(String endDate);

    boolean isEndDateValid(Date startDate, Date endDate, Stream stream);
    boolean isEmailValid(String email);

    //course data
    boolean isCourseNameValid(String courseName);

    //stream data
    boolean isStreamNameValid(String streamName);
    boolean isStreamDurationGreaterThan0(Long streamDuration);

    boolean isDateRangeValid(String startDate, String endDate);
}