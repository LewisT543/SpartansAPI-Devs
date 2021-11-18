package com.sparta.spartansapi.mappingservices;


import com.sparta.spartansapi.mongodb.models.Stream;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import com.sparta.spartansapi.utils.APIMessageResponse;
import com.sparta.spartansapi.utils.APIResponse;
import com.sparta.spartansapi.utils.ResponseManager;
import io.swagger.annotations.Api;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StreamService {

    private StreamRepository streamRepository;

    @Autowired
    public StreamService(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    public ResponseEntity<?> addStream(Stream stream) {
        try {
            return new ResponseEntity<>(new APIResponse(new ArrayList<>(List.of(streamRepository.insert(stream))), ResponseManager.RECORD_ADDED, 1, HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findAllStreams() {
        try {
            List<Stream> streams = new ArrayList<>(streamRepository.findAll());
            if(streams.isEmpty()) {
                return new ResponseEntity<>(new APIResponse(streams, ResponseManager.NO_RECORDS_FOUND,0, HttpStatus.OK.value()),  HttpStatus.OK);
            }
            return new ResponseEntity<>(new APIResponse(streams, ResponseManager.RECORDS_FOUND, streams.size(), HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findByName(String name){
        try {
            List<Stream> streams = streamRepository.getStreamsByNameContains(name);
            if (streams.isEmpty())
                return new ResponseEntity<>(new APIResponse(streams, ResponseManager.NO_RECORDS_FOUND,0, HttpStatus.OK.value()), HttpStatus.OK);
            return new ResponseEntity<>(new APIResponse(streams, ResponseManager.RECORDS_FOUND, streams.size(), HttpStatus.OK.value()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateStream(String id, Stream stream){
        try {
            Optional<Stream> streamData = streamRepository.findById(id);
            if (streamData.isPresent()) {
                Stream _stream = streamData.get();
                _stream.setName(stream.getName());
                _stream.setDuration(stream.getDuration());
                return new ResponseEntity<>(new APIResponse(new ArrayList<>(List.of(streamRepository.save(_stream))), ResponseManager.RECORD_ADDED, 1, HttpStatus.OK.value()), HttpStatus.OK);
            } else
                return new ResponseEntity<>(new APIMessageResponse(ResponseManager.NO_RECORD_FOUND),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteById(String id) {
        try{
            Optional<Stream> foundStream = streamRepository.findById(id);
            if (foundStream.isPresent()) {
                streamRepository.deleteById(id);
                return new ResponseEntity<>(new APIMessageResponse(ResponseManager.RECORD_DELETED), HttpStatus.OK);
            } else
                return new ResponseEntity<>(new APIMessageResponse(ResponseManager.NO_RECORD_FOUND),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new APIMessageResponse(ResponseManager.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
