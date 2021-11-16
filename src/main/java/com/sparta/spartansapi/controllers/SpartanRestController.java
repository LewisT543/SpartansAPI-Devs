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
@RequestMapping("/spartan")
public class SpartanRestController {

    @Autowired
    private SpartanService spartanService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllSpartans()  {
        return spartanService.getAllSpartans();
    }

    @GetMapping(value="/", params={"q"})
    public ResponseEntity<?> getSpartansByFullTextSearch(@RequestParam String q) {
        return spartanService.getSpartansByFullTextSearch(q);
    }

    @GetMapping(value="/", params={"firstname"})
    public ResponseEntity<?> getSpartansByFirstName(@RequestParam String firstname) {
        return spartanService.getAllSpartansByFirstName(firstname);
    }

    @GetMapping(value="/", params={"lastname"})
    public ResponseEntity<?> getSpartansByLastName(@RequestParam String lastname) {
        return spartanService.getAllSpartansByLastName(lastname);
    }

    @GetMapping(value="/name", params={"firstname", "lastname"})
    public ResponseEntity<?> getSpartansByFirstNameAndLastName(@RequestParam(required = true) String firstName,
                                                                           @RequestParam(required = false) String lastName){
        return spartanService.getAllSpartansByFirstNameAndLastName(firstName,lastName);
    }

//    @GetMapping(value="/start", params={"startdate"})
//    public ResponseEntity<?> getSpartansByStartDate(@RequestParam String startDate) {
//        if (Utilities.isParseableDate(startDate))
//            return spartanService.getSpartansByStartDateAfter(Utilities.parseStringToLocalDate(startDate));
//        else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//    }
//
//    @GetMapping(value="/range", params={"startdatelower", "startdateupper"})
//    public ResponseEntity<?> getSpartansByStartDate(@RequestParam String startDateMin,
//                                                                @RequestParam String startDateMax) {
//        if (Utilities.isParseableDate(startDateMin) && Utilities.isParseableDate(startDateMax))
//            return spartanService.getSpartansByStartDateBetween(Utilities.parseStringToLocalDate(startDateMin),
//                    Utilities.parseStringToLocalDate(startDateMax));
//        else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//    }

    @GetMapping(value="/start", params={"startdate"})
    public ResponseEntity<?> getSpartansByStartDate(@RequestParam String startDate) {
        if (Utilities.stringToDate(startDate) != null)
            return spartanService.getSpartansByStartDateAfter(Utilities.stringToDate(startDate));
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value="/range", params={"startdatelower", "startdateupper"})
    public ResponseEntity<?> getSpartansByStartDate(@RequestParam String startDateMin,
                                                    @RequestParam String startDateMax) {
        if ((Utilities.stringToDate(startDateMin) != null) && (Utilities.stringToDate(startDateMax) != null)) {
            return spartanService.getSpartansByStartDateBetween(Utilities.stringToDate(startDateMin),
                    Utilities.stringToDate(startDateMax));
        } else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }



//    @GetMapping(value="/course", params={"name"})
//    public ResponseEntity<?> getSpartansByCourseName(@RequestParam String course) {
//        return spartanService.getSpartansByCourseName(course);
//    }
//
//    @GetMapping(value="/stream", params={"name"})
//    public ResponseEntity<?> getSpartansByStreamName(@RequestParam String stream) {
//        return spartanService.getSpartansByStreamName(stream);
//    }

    @PostMapping(value="/add")
    public ResponseEntity<?> addNewSpartan(@RequestBody Spartan spartan) {
        return spartanService.addNewSpartan(spartan);
    }

    //// THIS IS WEIRD ATM ////
    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateSpartanById(@PathVariable("id") String id,
                                                    @RequestBody Spartan spartan) {
        return spartanService.updateSpartanById(id, spartan);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> deleteSpartanById(@PathVariable("id") String id) {
        return spartanService.deleteSpartanById(id);
    }

}
