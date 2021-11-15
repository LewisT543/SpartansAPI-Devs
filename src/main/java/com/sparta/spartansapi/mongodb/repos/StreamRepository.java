package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Stream;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StreamRepository extends MongoRepository<Stream, String> {
}
