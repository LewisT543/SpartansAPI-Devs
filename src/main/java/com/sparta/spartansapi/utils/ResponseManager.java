package com.sparta.spartansapi.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// yeet
public final class ResponseManager {
    private ResponseManager() {}

    public static final String NO_RECORD_FOUND = "No record found";
    public static final String NO_RECORDS_FOUND = "No records found";
    public static final String RECORDS_FOUND = "Records found";
    public static final String RECORD_FOUND = "Record found";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String RECORD_ADDED = "Record added";
    public static final String RECORD_DELETED = "Record deleted";
    public static final String RECORD_UPDATED = "Record updated";
    public static final String FIELD_FORMAT_INVALID = "Field format invalid";
}
