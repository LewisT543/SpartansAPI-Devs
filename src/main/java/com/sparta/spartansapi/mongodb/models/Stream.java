package com.sparta.spartansapi.mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "streams")
public class Stream {
    @Id private String _id;
    private String stream_name;
    private Integer stream_duration;

    public Stream(String _id, String stream_name, Integer stream_duration) {
        this._id = _id;
        this.stream_name = stream_name;
        this.stream_duration = stream_duration;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
