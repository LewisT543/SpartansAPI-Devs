package com.sparta.spartansapi.utils;

import com.sparta.spartansapi.mongodb.models.Stream;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Utilities {
    public static final HashMap<String, Integer> STREAM_DURATIONS = new HashMap<>() {{
        put("Java Dev",	10);
        put("Java SDET", 10);
        put("C# Dev", 11);
        put("C# SDET", 12);
        put("Data", 13);
        put("DevOps", 14);
        put("Cyber Security", 15);
        put("Business Analytics", 7);
        put("Test Analysis", 7);
        put("Product Owner", 7);
    }};
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static Date stringToDate(String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            return formatter.parse(date);
            //return new SimpleDateFormat(DATE_FORMAT).parse(date).toInstant();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);
    }

    public static Date calculateEndDate(Date startDate, Stream stream) {
        return DateUtils.addWeeks(startDate, STREAM_DURATIONS.get(stream.getName()));
    }

    public static boolean datesAreValid(Date startDate, Date endDate) {
        return endDate.before(startDate);
    }
}

