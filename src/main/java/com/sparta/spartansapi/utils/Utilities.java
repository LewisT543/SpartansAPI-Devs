package com.sparta.spartansapi.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utilities {
    public static boolean isParseableDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            LocalDate.parse(dateString, dtf);
            return true;
        } catch (DateTimeParseException pe) {
            pe.printStackTrace();
        }
        return false;
    }

    public static LocalDate parseStringToLocalDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            return LocalDate.parse(dateString, dtf);
        } catch (DateTimeParseException pe) {
            pe.printStackTrace();
        }
        return LocalDate.parse(dateString, dtf);
    }
}
