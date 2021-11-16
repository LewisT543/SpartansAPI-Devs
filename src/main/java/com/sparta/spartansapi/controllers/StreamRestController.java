package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.dtos.StreamDTO;
import com.sparta.spartansapi.mappingservices.StreamService;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/streams")
public class StreamRestController {

    @Autowired
    private StreamService streamService;

    public StreamRestController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/all")
    public List<StreamDTO> findAllStreams() {
        return streamService.findAll();
    }

}
