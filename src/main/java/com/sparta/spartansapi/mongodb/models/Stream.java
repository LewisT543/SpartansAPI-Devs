package com.sparta.spartansapi.mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "streams")
public class Stream {
    @Id private String _id;
    @Field(value = "streamname")
    private String streamname;
    @Field(value = "streamduration")
    private Integer streamduration;

    public Stream(String _id, String stream_name, Integer stream_duration) {
        this._id = _id;
        this.streamname = stream_name;
        this.streamduration = stream_duration;
    }

    public Stream(){

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStreamname() {
        return streamname;
    }

    public void setStreamname(String streamname) {
        this.streamname = streamname;
    }

    public Integer getStreamduration() {
        return streamduration;
    }

    public void setStreamduration(Integer streamduration) {
        this.streamduration = streamduration;
    }
}
