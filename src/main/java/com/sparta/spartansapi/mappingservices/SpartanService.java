package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.mongodb.repos.SpartanRepository;
import com.sparta.spartansapi.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SpartanService {

    private SpartanRepository spartanRepository;

    @Autowired
    public SpartanService(SpartanRepository spartanRepository) {
        this.spartanRepository = spartanRepository;
    }

    public ResponseEntity<?> addNewSpartan(Spartan spartan){
        // Currently doesn't check for duplicate spartans
        try {
            Spartan newSpartan = spartanRepository.insert(new Spartan(spartan.getFirstName(), spartan.getMiddleName(), spartan.getLastName(),
                    spartan.getStartDate(), spartan.getCourse(), spartan.getStream(), spartan.getEmail(),
                    Utilities.calculateEndDate(spartan.getStartDate(), spartan.getStream())));
            return new ResponseEntity<>(newSpartan, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllSpartans() {
        try {
            List<Spartan> spartans = new ArrayList<>(spartanRepository.findAll());
            if(spartans.isEmpty())
                return new ResponseEntity<>("No Spartans found.", HttpStatus.OK);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getSpartanById(String id) {
        try {
            Optional<Spartan> spartan = spartanRepository.findById(id);
            if (spartan.isEmpty())
                return new ResponseEntity<>("No Spartans found with id: " + id, HttpStatus.OK);
            return new ResponseEntity<>(spartan, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getSpartansByStartDateAfter(Date startDate) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByStartDateAfter(startDate);
            if(spartans.isEmpty())
                return new ResponseEntity<>("No Spartans found with start date after: " + startDate, HttpStatus.OK);
        return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getSpartansByStartDateBetween(Date startDateMin, Date startDateMax) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByStartDateBetween(startDateMin, startDateMax);
            if(spartans.isEmpty())
                return new ResponseEntity<>("No Spartans found between: " + startDateMin
                        + " and " + startDateMax, HttpStatus.OK);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?>getSpartansByCourseName(String courseName) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByCourseName(courseName);
            if(spartans.isEmpty())
                return new ResponseEntity<>("No Spartans found with course name: " + courseName, HttpStatus.OK);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?>getSpartansByStreamName(String streamName) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByStreamName(streamName);
            if(spartans.isEmpty())
                return new ResponseEntity<>("No Spartans found with stream name: " + streamName, HttpStatus.OK);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?>getSpartansByFullTextSearch(String text) {
        try {
            List<Spartan> spartans = spartanRepository.findAll(getMatcherExample(text));
            if(spartans.isEmpty())
                return new ResponseEntity<>("No Spartans found with text: " + text, HttpStatus.OK);
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper method for above
    private Example<Spartan> getMatcherExample(String text) {
        Spartan spartan = new Spartan();
        spartan.setFirstName(text);
        spartan.setLastName(text);
        spartan.setMiddleName(text);
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("middleName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return Example.of(spartan, customExampleMatcher);
    }

    public ResponseEntity<?> updateSpartanById(String id, Spartan spartan) {
        try {
            Optional<Spartan> foundSpartan = spartanRepository.findById(id);
            if (foundSpartan.isPresent()) {
                Spartan updatedSpartan = new Spartan(spartan.getId(), spartan.getFirstName(), spartan.getMiddleName(), spartan.getLastName(),
                        spartan.getStartDate(), spartan.getCourse(), spartan.getStream(), spartan.getEmail(),
                        Utilities.calculateEndDate(spartan.getStartDate(), spartan.getStream()));
                return new ResponseEntity<>(spartanRepository.save(updatedSpartan), HttpStatus.OK);
            } else
                return new ResponseEntity<>("Cannot update Spartan with id: " + id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<?> deleteSpartanById(String id) {
        try {
            Optional<Spartan> foundSpartan = spartanRepository.findById(id);
            if (foundSpartan.isPresent()) {
                spartanRepository.deleteById(id);
                return new ResponseEntity<>("Spartan Deleted with id: " + id, HttpStatus.OK);
            } else
                return new ResponseEntity<>("Cannot delete Spartan with id: " + id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
