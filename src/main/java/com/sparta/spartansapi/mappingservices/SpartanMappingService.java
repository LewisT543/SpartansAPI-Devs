package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.mongodb.repos.SpartanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpartanMappingService {
    @Autowired
    private SpartanRepository spartanRepository;
    private MongoTemplate mongoTemplate;

    public ResponseEntity<List<Spartan>> getAllSpartans() {
        try {
            List<Spartan> spartans = new ArrayList<>(spartanRepository.findAll());
            if(spartans.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Spartan>> getAllSpartansByFirstName(String firstName) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByFirstName(firstName);
            if(spartans.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Spartan>> getAllSpartansByLastName(String lastName){
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByLastName(lastName);
            if(spartans.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Spartan>> getSpartansByStartDateAfter(LocalDate startDate) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByStartDateAfter(startDate);
            if(spartans.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Spartan>> getSpartansByCourseName(String courseName) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByCourseName(courseName);
            if(spartans.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Spartan>> getSpartansByFullTextSearch(String text) {
        TextCriteria textCriteria = TextCriteria
                .forDefaultLanguage()
                .matching(text);
        Query byFreeText = TextQuery.queryText(textCriteria)
                .sortByScore();
        try {
            List<Spartan> spartans = this.mongoTemplate.find(byFreeText, Spartan.class);
            if(spartans.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Spartan> addNewSpartan(Spartan spartan){
        try {
            Spartan newSpartan = spartanRepository.insert(new Spartan(spartan.getFirstName(), spartan.getMiddleName(), spartan.getLastName(),
                    spartan.getStartDate(), spartan.getCourse(), spartan.getStream(), spartan.getEmail()));
            return new ResponseEntity<>(newSpartan, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Spartan> updateSpartanById(String id, Spartan spartan) {
        Optional<Spartan> foundSpartan = spartanRepository.findById(id);
        if (foundSpartan.isPresent()) {
            Spartan unpackedSpartan = foundSpartan.get();
            unpackedSpartan.setFirstName(spartan.getFirstName());
            unpackedSpartan.setMiddleName(spartan.getMiddleName());
            unpackedSpartan.setLastName(spartan.getLastName());
            unpackedSpartan.setStartDate(spartan.getStartDate());
            unpackedSpartan.setCourse(spartan.getCourse());
            unpackedSpartan.setStream(spartan.getStream());
            unpackedSpartan.setEmail(spartan.getEmail());
            return new ResponseEntity<>(spartanRepository.save(unpackedSpartan), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteSpartanById(String id) {
        try {
            spartanRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}