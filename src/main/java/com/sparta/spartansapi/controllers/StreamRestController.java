package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.mappingservices.classes.StreamMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/streams")
public class StreamRestController {

    @Autowired
    private StreamMappingService streamMappingService;
}
