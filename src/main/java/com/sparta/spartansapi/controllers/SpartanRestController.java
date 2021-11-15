package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.mappingservices.SpartanService;
import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spartans")
public class SpartanRestController {

    @Autowired
    private SpartanService spartanService;

    @GetMapping("/all")
    public ResponseEntity<List<Spartan>> getAllSpartans()  {
        return spartanService.getAllSpartans();
    }

    @GetMapping("/")
    public ResponseEntity<List<Spartan>> getSpartansByFullTextSearch(@RequestParam String text) {
        return spartanService.getSpartansByFullTextSearch(text);
    }

    @GetMapping("/")
    public ResponseEntity<List<Spartan>> getSpartansByFirstName(@RequestParam String firstName) {
        return spartanService.getAllSpartansByFirstName(firstName);
    }

    @GetMapping("/")
    public ResponseEntity<List<Spartan>> getSpartansByLastName(@RequestParam String lastName) {
        return spartanService.getAllSpartansByLastName(lastName);
    }

    @GetMapping("/")
    public ResponseEntity<List<Spartan>> getSpartansByStartDate(@RequestParam String startDate) {
        if (Utilities.isParseableDate(startDate))
            return spartanService.getSpartansByStartDateAfter(Utilities.parseStringToLocalDate(startDate));
        else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public ResponseEntity<List<Spartan>> getSpartansByCourseName(@RequestParam String course) {
        return spartanService.getSpartansByCourseName(course);
    }

    @PostMapping("/")
    public ResponseEntity<Spartan> addNewSpartan(@RequestBody Spartan spartan) {
        return spartanService.addNewSpartan(spartan);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Spartan> updateSpartanById(@PathVariable("id") String id,
                                                 @RequestBody Spartan spartan) {
        return spartanService.updateSpartanById(id, spartan);
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteSpartanById(@PathVariable("id") String id) {
        return spartanService.deleteSpartanById(id);
    }

}
