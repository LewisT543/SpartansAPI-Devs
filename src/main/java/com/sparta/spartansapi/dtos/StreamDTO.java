package com.sparta.spartansapi.dtos;

public class StreamDTO {
    private String stream_name;
    private Integer stream_duration;

    public StreamDTO(String stream_name, Integer stream_duration) {
        this.stream_name = stream_name;
        this.stream_duration = stream_duration;
    }

    public StreamDTO() {

    }

    public String getStream_name() {
        return stream_name;
    }

    public void setStream_name(String stream_name) {
        this.stream_name = stream_name;
    }

    public Integer getStream_duration() {
        return stream_duration;
    }

    public void setStream_duration(Integer stream_duration) {
        this.stream_duration = stream_duration;
    }
}
