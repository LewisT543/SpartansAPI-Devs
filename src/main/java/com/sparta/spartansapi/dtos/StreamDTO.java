package com.sparta.spartansapi.dtos;

@Deprecated(forRemoval = true)
public class StreamDTO {
    private String streamname;
    private Integer streamduration;

    public StreamDTO(String streamname, Integer streamduration) {
        this.streamname = streamname;
        this.streamduration = streamduration;
    }

    public StreamDTO() {

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
