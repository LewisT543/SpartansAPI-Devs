package com.sparta.spartansapi.mappingservices;


import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.mongodb.models.Stream;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
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
            return new ResponseEntity<>(streamRepository.insert(stream), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findAllStreams() {
        try {
            List<Stream> streams = new ArrayList<>(streamRepository.findAll());
            if(streams.isEmpty())
                return new ResponseEntity<>("No Streams found",  HttpStatus.OK);
            return new ResponseEntity<>(streams, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findByName(String name){
        try {
            List<Stream> streams = streamRepository.getStreamsByNameContains(name);
            if (streams.isEmpty())
                return new ResponseEntity<>("No Streams found: " + name, HttpStatus.OK);
            return new ResponseEntity<>(streams, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Unexpected Server error.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateStream(String id, Stream stream){
        try {
            Optional<Stream> streamData = streamRepository.findById(id);
            if (streamData.isPresent()){
                Stream _stream = streamData.get();
                _stream.setName(stream.getName());
                _stream.setDuration(stream.getDuration());
                return new ResponseEntity<>(streamRepository.save(_stream), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("Stream not found",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteById(String id) {
        try{
            Optional<Stream> foundStream = streamRepository.findById(id);
            if (foundStream.isPresent()) {
                streamRepository.deleteById(id);
                return new ResponseEntity<>("Stream has been deleted: "+ id, HttpStatus.OK);
            } else
                return new ResponseEntity<>("Cannot delete Stream with id: " + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected Server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
