package com.sparta.spartansapi.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class Utilities {
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
}
