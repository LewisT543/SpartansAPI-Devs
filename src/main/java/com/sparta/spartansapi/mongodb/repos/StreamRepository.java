package com.sparta.spartansapi.mongodb.repos;

import com.sparta.spartansapi.mongodb.models.Stream;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StreamRepository extends MongoRepository<Stream, String> {

    List<Stream> findAll();

    List<Stream> findStreamByName(String streamName);

    void addStream(String streamName);

    void updateById(String streamID);

    void deleteById(String streamID);
}
