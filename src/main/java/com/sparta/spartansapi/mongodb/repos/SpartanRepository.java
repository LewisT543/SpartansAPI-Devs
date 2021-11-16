package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Spartan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SpartanRepository extends MongoRepository<Spartan, String> {

    List<Spartan> getSpartansByFirstName(String firstName);
    List<Spartan> getSpartansByLastName(String lastName);
    List<Spartan> getSpartansByFirstNameAndLastName(String firstName, String lastName);
    List<Spartan> getSpartansByStartDateAfter(Date startDate);
    List<Spartan> getSpartansByStartDateBetween(Date startDateMin, Date startDateMax);
//    List<Spartan> getSpartansByCourseName(String courseName);
//    List<Spartan> getSpartansByStreamName(String streamName);

}
