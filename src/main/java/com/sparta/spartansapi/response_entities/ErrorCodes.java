package com.sparta.spartansapi.response_entities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ErrorCodes {

    private ErrorCodes() {}

    public static final ResponseEntity<?> NO_SPARTANS_FOUND = ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body("No spartans found");

    public static final ResponseEntity<?> NO_RECORDS_FOUND = ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body("No records found");

    public static final ResponseEntity<?> NO_RECORD_FOUND = ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body("No record found");

    public static final ResponseEntity RECORD_DELETED = ResponseEntity
            .status(HttpStatus.OK)
            .body("Record deleted");

    public static final ResponseEntity NEW_RECORD = ResponseEntity
            .status(HttpStatus.CREATED)
            .body("New record created");

    public static final ResponseEntity RECORD_UPDATED = ResponseEntity
            .status(HttpStatus.OK)
            .body("Record updated");

    public static final ResponseEntity INTERNAL_SERVER_ERROR = ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Internal server error");

    public static final ResponseEntity NO_MATCHES_FOUND = ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body("No matching record found");
}
