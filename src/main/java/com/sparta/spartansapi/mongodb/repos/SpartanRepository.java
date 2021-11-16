package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Spartan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface SpartanRepository extends MongoRepository<Spartan, String> {

    /*
    List<Spartan> getSpartansByFirstName(String firstName);
    List<Spartan> getSpartansByLastName(String lastName);
    List<Spartan> getSpartansByStartDateAfter(LocalDate startDate);
    List<Spartan> getSpartansByCourseName(String courseName);

    void addNewSpartan(Spartan spartan);
    void updateSpartanById(String id, Spartan spartan);
    void deleteSpartanById(String id);
    
     */

}
