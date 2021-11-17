package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.mongodb.models.Stream;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import com.sparta.spartansapi.response_entities.ErrorCodes;
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

    public ResponseEntity<?> findAllStreams() {
        try {
            List<Stream> streams = new ArrayList<>(streamRepository.findAll());
            if(streams.isEmpty())
                return ErrorCodes.NO_SPARTANS_FOUND;
            return new ResponseEntity<>(streams, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> updateStream(String id, Stream stream) {
        Optional<Stream> streamData = streamRepository.findById(id);
        if (streamData.isPresent()) {
            Stream _stream = streamData.get();
            _stream.setName(stream.getName());
            _stream.setDuration(stream.getDuration());
            streamRepository.save(_stream);
            return ErrorCodes.RECORD_UPDATED;
        } else return ErrorCodes.NO_RECORD_FOUND;
    }

    public ResponseEntity<?> deleteById(String id) {
        try {
            if (streamRepository.existsById(id)) {
                streamRepository.deleteById(id);
                return ErrorCodes.RECORD_DELETED;
            } else {
                return ErrorCodes.NO_MATCHES_FOUND;
            }
        } catch (Exception e) {
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> findByName(String name) {
        try {
            List<Stream> streams = streamRepository.getStreamsByNameContains(name);
            if (streams.isEmpty())
                return ErrorCodes.NO_MATCHES_FOUND;
            return new ResponseEntity<>(streams, HttpStatus.OK);
        } catch (Exception e) {
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }

    public ResponseEntity<?> addCourse(Stream stream) {
        try {
            streamRepository.insert(stream);
            return ErrorCodes.NEW_RECORD;
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodes.INTERNAL_SERVER_ERROR;
        }
    }
}
