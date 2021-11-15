package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Spartan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpartanRepository extends MongoRepository<Spartan, String> {
}
