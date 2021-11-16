package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.dtos.StreamDTO;
import com.sparta.spartansapi.mappingservices.StreamService;
import com.sparta.spartansapi.mongodb.models.Stream;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Stream> updateStreamById(@PathVariable("id") String id, @RequestBody Stream stream)
    {
        return streamService.updateStream(id,stream);
    }



}
