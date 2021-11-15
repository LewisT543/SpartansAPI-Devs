package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.mappingservices.SpartanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spartans")
public class SpartanRestController {

    @Autowired
    private SpartanMappingService spartanMappingService;

}
