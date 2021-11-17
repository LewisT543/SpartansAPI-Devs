package com.sparta.spartansapi.controllers;

import com.sparta.spartansapi.mappingservices.SpartanService;
import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SpartanRestController {

    private SpartanService spartanService;

    @Autowired
    public SpartanRestController(SpartanService spartanService) {
        this.spartanService = spartanService;
    }

    @GetMapping("/spartans")
    public ResponseEntity<?> getAllSpartans()  {
        return spartanService.getAllSpartans();
    }

    @GetMapping("/spartans/{id}")
    public ResponseEntity<?> getSpartansById(@PathVariable String id) {
        return spartanService.getSpartanById(id);
    }

    @GetMapping(value="/spartans", params={"q"})
    public ResponseEntity<?> getSpartansByFullTextSearch(@RequestParam String q) {
        return spartanService.getSpartansByFullTextSearch(q);
    }

    @GetMapping(value="/spartans", params={"startdate"})
    public ResponseEntity<?> getSpartansByStartDate(@RequestParam String startdate) {
        if (Utilities.stringToDate(startdate) != null)
            return spartanService.getSpartansByStartDateAfter(Utilities.stringToDate(startdate));
        else
            return new ResponseEntity<>("Invalid Date entry, please use yyyy-MM-dd format.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/spartans/range", params={"dateafter", "datebefore"})
    public ResponseEntity<?> getSpartansByStartDateBetween(@RequestParam String dateafter,
                                                            @RequestParam String datebefore) {
        if (Utilities.datesAreValid(Utilities.stringToDate(dateafter), Utilities.stringToDate(datebefore)))
            return new ResponseEntity<>("Invalid Date entry, make sure datebefore is ealier than dateafter.", HttpStatus.BAD_REQUEST);
        if ((Utilities.stringToDate(dateafter) != null) && (Utilities.stringToDate(datebefore) != null)) {
            return spartanService.getSpartansByStartDateBetween(Utilities.stringToDate(dateafter),
                    Utilities.stringToDate(datebefore));
        } else
            return new ResponseEntity<>("Invalid Date entry, please use yyyy-MM-dd format.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/spartans", params={"course"})
    public ResponseEntity<?> getSpartansByCourseName(@RequestParam String course) {
        return spartanService.getSpartansByCourseName(course);
    }

    @GetMapping(value="/spartans", params={"stream"})
    public ResponseEntity<?> getSpartansByStreamName(@RequestParam String stream) {
        return spartanService.getSpartansByStreamName(stream);
    }

    @PostMapping(value="/spartans")
    public ResponseEntity<?> addNewSpartan(@RequestBody Spartan spartan) {
        return spartanService.addNewSpartan(spartan);
    }

    @PutMapping(value="/spartans/{id}")
    public ResponseEntity<?> updateSpartanById(@PathVariable("id") String id,
                                                    @RequestBody Spartan spartan) {
        return spartanService.updateSpartanById(id, spartan);
    }

    @DeleteMapping(value="/spartans/{id}")
    public ResponseEntity<?> deleteSpartanById(@PathVariable("id") String id) {
        return spartanService.deleteSpartanById(id);
    }
}
