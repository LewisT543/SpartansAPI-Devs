package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.mappingservices.StreamService;
import com.sparta.spartansapi.mongodb.models.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StreamRestController {

    private StreamService streamService;

    @Autowired
    public StreamRestController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/streams")
    public ResponseEntity<?> findAllStreams() {
        return streamService.findAllStreams();
    }

    @PutMapping(value = "/streams/{id}")
    public ResponseEntity<Stream> updateStreamById(@PathVariable("id") String id, @RequestBody Stream stream)
    {
        return streamService.updateStream(id,stream);
    }

    @DeleteMapping(value = "/streams/{id}")
    public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable("id") String id) {
        return streamService.deleteById(id);
    }

    @GetMapping(value = "/streams", params = {"name"})
    public ResponseEntity<List<Stream>> getAllCoursesByName(@RequestParam String name) {
        return streamService.findByName(name);
    }

    @PostMapping("/streams")
    public ResponseEntity<Stream> addCourse(@RequestBody Stream stream) {
        return streamService.addStream(stream);
    }



}
