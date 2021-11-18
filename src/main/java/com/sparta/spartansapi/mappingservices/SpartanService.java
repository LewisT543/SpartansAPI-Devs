package com.sparta.spartansapi.mappingservices;

import com.mongodb.MongoSocketReadTimeoutException;
import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.mongodb.repos.SpartanRepository;
import com.sparta.spartansapi.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpartanService {

    private SpartanRepository spartanRepository;
    private InputValidator validator = new InputValidator();

    @Autowired
    public SpartanService(SpartanRepository spartanRepository) {
        this.spartanRepository = spartanRepository;
    }

    public ResponseEntity<?> addNewSpartan(Spartan spartan) {
        // Currently doesn't check for duplicate spartans
        try {
            if (validator.isSpartanValid(spartan)) {
                Spartan newSpartan = spartanRepository.insert(new Spartan(spartan.getFirstName(), spartan.getMiddleName(), spartan.getLastName(),
                        spartan.getStartDate(), spartan.getCourse(), spartan.getStream(), spartan.getEmail(),
                        Utilities.calculateEndDate(spartan.getStartDate(), spartan.getStream())));
                return new ResponseEntity<>(new APIResponse(new ArrayList<>(List.of(newSpartan)), ResponseManager.RECORD_ADDED, 1, HttpStatus.CREATED.value()), HttpStatus.CREATED);
            } else return new ResponseEntity<>(new APIMessageResponse(ResponseManager.FIELD_FORMAT_INVALID), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllSpartans() {
        try {
            List<Spartan> spartans = new ArrayList<>(spartanRepository.findAll());
            if(spartans.isEmpty())
                return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.NO_RECORDS_FOUND, 0, HttpStatus.OK.value()), HttpStatus.OK);
            return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.RECORDS_FOUND, spartans.size(), HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getSpartanById(String id) {
        try {
            Optional<Spartan> spartan = spartanRepository.findById(id);
            if (spartan.isEmpty())
                return new ResponseEntity<>(new APIResponse(new ArrayList<>(), ResponseManager.NO_RECORD_FOUND, 0, HttpStatus.OK.value()), HttpStatus.OK);
            return new ResponseEntity<>(new APIResponse(new ArrayList<>(List.of(spartan)), ResponseManager.RECORD_FOUND, 1, HttpStatus.OK.value()) , HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getSpartansByStartDateAfter(Date startDate) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByStartDateAfter(startDate);
            if(spartans.isEmpty())
                return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.NO_RECORDS_FOUND, 0, HttpStatus.OK.value()), HttpStatus.OK);
        return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.RECORDS_FOUND, spartans.size(), HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getSpartansByStartDateBetween(Date startDateMin, Date startDateMax) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByStartDateBetween(startDateMin, startDateMax);
            if(spartans.isEmpty())
                // Leave this error as a manual implementation - it is only used once and our solution does not work for this
                return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.NO_RECORDS_FOUND, 0, HttpStatus.OK.value()), HttpStatus.OK);
            return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.RECORDS_FOUND, spartans.size(), HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?>getSpartansByCourseName(String courseName) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByCourseName(courseName);
            if (spartans.isEmpty()) {
                //throw new ResponseStatusException(HttpStatus.NO_CONTENT,  "No record found");
                return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.NO_RECORDS_FOUND, 0, HttpStatus.OK.value()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.RECORDS_FOUND, spartans.size(), HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?>getSpartansByStreamName(String streamName) {
        try {
            List<Spartan> spartans = spartanRepository.getSpartansByStreamName(streamName);
            if(spartans.isEmpty())
                return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.NO_RECORDS_FOUND, 0, HttpStatus.OK.value()), HttpStatus.OK);
            return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.RECORDS_FOUND, spartans.size(), HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?>getSpartansByFullTextSearch(String text) {
        try {
            //List<Spartan> spartans = spartanRepository.findAll(getMatcherExample(text));
            List<Spartan> spartans = findMatches(text);
            if(spartans.isEmpty()) {
                return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.NO_RECORDS_FOUND, 0, HttpStatus.OK.value()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new APIResponse(spartans, ResponseManager.RECORDS_FOUND, spartans.size(), HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Spartan> findMatches(String query) {
        List<String> fragments = List.of(query.split(" "));
        List<Spartan> spartans = spartanRepository.findAll()
                .parallelStream()
                .filter((s) -> {
                    int matches = 0;
                    String fullName = s.getFirstName() + " " + s.getMiddleName() + " " + s.getLastName();
                    for(String f : fragments) {
                        if (fullName.contains(f))
                            matches = matches + 1;
                    }
                    double score = ((double) matches / fragments.size()) * 100;
                    return score >= 50;
                }).collect(Collectors.toList());
        return spartans;
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
                return new ResponseEntity<>(new APIResponse(new ArrayList<>(List.of(spartanRepository.save(updatedSpartan))), ResponseManager.RECORD_UPDATED, 1, HttpStatus.OK.value()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new APIMessageResponse(ResponseManager.NO_RECORD_FOUND), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteSpartanById(String id) {
        try {
            Optional<Spartan> foundSpartan = spartanRepository.findById(id);
            if (foundSpartan.isPresent()) {
                spartanRepository.deleteById(id);
                return new ResponseEntity<>(new APIMessageResponse(ResponseManager.RECORD_DELETED), HttpStatus.OK);
            } else
                return new ResponseEntity<>(new APIMessageResponse(ResponseManager.NO_RECORD_FOUND), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
