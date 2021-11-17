package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.mongodb.repos.SpartanRepository;
import com.sparta.spartansapi.response_entities.ErrorCodes;
import com.sparta.spartansapi.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.cert.CertificateRevokedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SpartanService {
    @Autowired
    private SpartanRepository spartanRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public ResponseEntity<?> addNewSpartan(Spartan spartan) {
        // Currently doesn't check for duplicate spartans by First+middle+last names
        try {
            spartanRepository.insert(new Spartan(spartan.getFirstName(), spartan.getMiddleName(),
                    spartan.getLastName(), spartan.getStartDate(), spartan.getCourse(), spartan.getStream(),
                    spartan.getEmail(), Utilities.calculateEndDate(spartan.getStartDate(), spartan.getStream())));
            return ErrorCodes.NEW_RECORD;
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> getAllSpartans() {
        try {
            List<Spartan> spartans = new ArrayList<>(spartanRepository.findAll());
            if(spartans.isEmpty())
                return ErrorCodes.NO_RECORDS_FOUND;
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> getAllSpartansByFirstName(String firstName) {
        System.out.println("ByFirstName sout");
        System.err.println("Pre-tryCatch byFirstname");
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByFirstNameContains(firstName);
            System.err.println("ByFirstName: Size: " + spartans.size());
            if(spartans.isEmpty())
                return ErrorCodes.NO_MATCHES_FOUND;
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> getAllSpartansByLastName(String lastName){
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByLastName(lastName);
            if(spartans.isEmpty())
                return ErrorCodes.NO_RECORDS_FOUND;
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> getAllSpartansByFirstNameAndLastName(String firstName, String lastName) {
        System.out.println("byfirst+last sout");
        System.err.println("Pre-tryCatch byFirstname+Lastname");
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByFirstNameAndLastName(firstName, lastName);
            System.err.println("ByFirstNameAndLastName: Size: " + spartans.size());
            if(spartans.isEmpty())
                return ErrorCodes.NO_RECORDS_FOUND;
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> getSpartansByStartDateAfter(Date startDate) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByStartDateAfter(startDate);
            if(spartans.isEmpty())
                return ErrorCodes.NO_RECORDS_FOUND;
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> getSpartansByStartDateBetween(Date startDateMin, Date startDateMax) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByStartDateBetween(startDateMin, startDateMax);
            if(spartans.isEmpty())
                return ErrorCodes.NO_RECORDS_FOUND;
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

//    public ResponseEntity<?>getSpartansByCourseName(String courseName) {
//        try {
//            List<Spartan> spartans = spartanRepository.getSpartansByCourseName(courseName);
//            if(spartans.isEmpty())
//                return ErrorCodes.NO_RECORDS_FOUND;
//            return new ResponseEntity<>(spartans, HttpStatus.OK);
//        } catch (Exception e) {
//            return ErrorCodes.INTERNAL_SERVER_ERROR;
//        }
//    }
//
//    public ResponseEntity<?>getSpartansByStreamName(String streamName) {
//        try {
//            List<Spartan> spartans = spartanRepository.getSpartansByStreamName(streamName);
//            if(spartans.isEmpty())
//                return ErrorCodes.NO_RECORDS_FOUND;
//            return new ResponseEntity<>(spartans, HttpStatus.OK);
//        } catch (Exception e) {
//            return ErrorCodes.INTERNAL_SERVER_ERROR;
//        }
//    }

    public ResponseEntity<?>getSpartansByFullTextSearch(String text) {
        TextCriteria textCriteria = TextCriteria
                .forDefaultLanguage()
                .matching(text);
        Query byFreeText = TextQuery.queryText(textCriteria)
                .sortByScore();
        try {
            List<Spartan> spartans = this.mongoTemplate.find(byFreeText, Spartan.class);
            if(spartans.isEmpty())
                return ErrorCodes.NO_RECORDS_FOUND;
            return new ResponseEntity<>(spartans, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> updateSpartanById(String id, Spartan spartan) {
        Optional<Spartan> foundSpartan = spartanRepository.findById(id);
           if (foundSpartan.isPresent()) {
            Spartan updatedSpartan = new Spartan(spartan.getFirstName(), spartan.getMiddleName(), spartan.getLastName(),
                    spartan.getStartDate(), spartan.getCourse(), spartan.getStream(), spartan.getEmail(),
                    Utilities.calculateEndDate(spartan.getStartDate(), spartan.getStream()));
            spartanRepository.save(updatedSpartan);
            return ErrorCodes.RECORD_UPDATED;
        } else return ErrorCodes.NO_RECORD_FOUND;
    }

    public ResponseEntity<?> deleteSpartanById(String id) {
        try {
            if (spartanRepository.existsById(id)) {
                spartanRepository.deleteById(id);
                return ErrorCodes.RECORD_DELETED;
            }
            return ErrorCodes.NO_MATCHES_FOUND;
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }
}
