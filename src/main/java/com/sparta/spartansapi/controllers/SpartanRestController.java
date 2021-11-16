package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.dtos.SpartanDTO;
import com.sparta.spartansapi.mappingservices.SpartanService;
import com.sparta.spartansapi.mongodb.repos.SpartanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spartans")
public class SpartanRestController {

    @Autowired
    private SpartanService spartanService;

    public SpartanRestController(SpartanService spartanService) {
        this.spartanService = spartanService;
    }

    @GetMapping("/all")
    public List<SpartanDTO> findAllSpartans() {
        return spartanService.findAll();
    }
}
