package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Spartan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SpartanRepository extends MongoRepository<Spartan, String> {

    List<Spartan> getSpartansByStartDateAfter(Date startDate);
    List<Spartan> getSpartansByStartDateBetween(Date startDateMin, Date startDateMax);

    @Query("{ 'stream.name' : {$regex : /.*?0.*/ }}")
    List<Spartan> getSpartansByStreamName(String name);

    @Query("{ 'course.name' : {$regex : /.*?0.*/ }}")
    List<Spartan> getSpartansByCourseName(String name);
}
