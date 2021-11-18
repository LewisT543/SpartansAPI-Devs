package com.sparta.spartansapi.utils;

import com.sparta.spartansapi.mongodb.models.Spartan;

import java.util.ArrayList;
import java.util.List;

public class APIResponse {
    private List<?> results;
    private String message;
    private Integer count;
    private Integer status_code;

    public APIResponse(List<?> results, String message, Integer count, Integer status_code) {
        this.results = results;
        this.message = message;
        this.count = count;
        this.status_code = status_code;
    }

    public List<?> getResults() {
        return results;
    }

    public void setResults(List<?> results) {
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }
}
