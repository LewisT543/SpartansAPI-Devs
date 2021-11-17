package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.dtos.StreamDTO;
import com.sparta.spartansapi.mappingservices.StreamService;
import com.sparta.spartansapi.mongodb.models.Stream;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> findAllStreams() {
        return streamService.findAllStreams();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateStreamById(@PathVariable("id") String id, @RequestBody Stream stream) {
        return streamService.updateStream(id,stream);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable("id") String id) {
        return streamService.deleteById(id);
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<?> getAllCoursesByName(@RequestParam String name) {
        return streamService.findByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody Stream stream) {
        return streamService.addCourse(stream);
    }



}
