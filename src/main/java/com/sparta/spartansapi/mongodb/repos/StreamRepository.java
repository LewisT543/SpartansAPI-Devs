package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Stream;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamRepository extends MongoRepository<Stream, String> {
    List<Stream> getStreamsByNameContains(String name);
}
