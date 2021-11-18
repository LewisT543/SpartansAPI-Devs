package com.sparta.spartansapi.utils;

import com.sparta.spartansapi.mongodb.models.Course;
import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.mongodb.models.Stream;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class InputValidator implements Validator{
    @Override
    public boolean isSpartanValid(Spartan spartan) {
        // don't mind me, just chaining a billion function calls together
        return isFirstNameNotNull(spartan.getFirstName()) &&
                isLastNameNotNull(spartan.getLastName()) &&
                isStartDateNotNull(spartan.getStartDate()) &&
                isCourseNotNull(spartan.getCourse()) &&
                isStreamNotNull(spartan.getStream()) &&
                isEmailNotNull(spartan.getEmail()) &&
                isEndDateNotNull(spartan.getEndDate()) &&
                isEmailValid(spartan.getEmail()) &&
                isEndDateValid(spartan.getStartDate(), spartan.getEndDate(), spartan.getStream()) &&
                isCourseValid(spartan.getCourse()) &&  // short-circuiting will mean if course is null, this check won't get performed
                isStreamValid(spartan.getStream());

    }

    public boolean isInputSpartanValid(Spartan spartan) {
        return isFirstNameNotNull(spartan.getFirstName()) &&
                isLastNameNotNull(spartan.getLastName()) &&
                isStartDateNotNull(spartan.getStartDate()) &&
                isCourseNotNull(spartan.getCourse()) &&
                isStreamNotNull(spartan.getStream()) &&
                isEmailNotNull(spartan.getEmail());
    }

    @Override
    public boolean isCourseValid(Course course) {
        return isCourseNameNotNull(course.getName()) && isCourseNameValid(course.getName());
    }

    @Override
    public boolean isStreamValid(Stream stream) {
        return isStreamNameNotNull(stream.getName()) &&
                isStreamDurationNotNull(stream.getDuration()) &&
                isStreamDurationGreaterThan0(stream.getDuration()) &&
                isStreamNameValid(stream.getName());
    }

    @Override
    public boolean isFirstNameNotNull(String firstName) {
        return firstName != null;
    }

    @Override
    public boolean isLastNameNotNull(String lastName) {
        return lastName != null;
    }

    @Override
    public boolean isStartDateNotNull(Date startDate) {
        return startDate != null;
    }

    @Override
    public boolean isCourseNotNull(Course course) {
        return course != null;
    }

    @Override
    public boolean isStreamNotNull(Stream stream) {
        return stream != null;
    }

    @Override
    public boolean isEmailNotNull(String email) {
        return email != null;
    }

    @Override
    public boolean isEndDateNotNull(Date endDate) {
        return endDate != null;
    }

    @Override
    public boolean isEndDateValid(Date startDate, Date endDate, Stream stream) {
        // start date occurs before end date
        Long duration = stream.getDuration();
        if (startDate == null || endDate == null) {
            return false;
        }
        Date calcEnd = DateUtils.addWeeks(startDate, Math.toIntExact(duration));
        if (endDate.before(startDate)) {
            return false;
        }
        if (endDate.compareTo(calcEnd) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailValid(String email) {
        return email.matches(".+@spartaglobal.com");
    }

    public boolean isCourseNameNotNull(String courseName) {
        return courseName != null;
    }

    public boolean isStreamNameNotNull(String streamName) {
        return streamName != null;
    }

    public boolean isStreamDurationNotNull(Long duration) {
        return duration != null;
    }

    @Override
    public boolean isCourseNameValid(String courseName) {
        return courseName.matches("Business \\d+") || courseName.matches("Engineering \\d+");
    }

    @Override
    public boolean isStreamNameValid(String streamName) {
        return Utilities.STREAM_DURATIONS.containsKey(streamName);
    }

    @Override
    public boolean isStreamDurationGreaterThan0(Long streamDuration) {
        return streamDuration>0;
    }

    @Override
    public boolean isDateRangeValid(String startDate, String endDate) {
        Date dateTypeStartDate = Utilities.stringToDate(startDate);
        Date dateTypeEndDate = Utilities.stringToDate(endDate);
        if (dateTypeEndDate == null || dateTypeStartDate == null){
            return false;
        }
        else {
            return dateTypeStartDate.before(dateTypeEndDate);
        }
    }
}