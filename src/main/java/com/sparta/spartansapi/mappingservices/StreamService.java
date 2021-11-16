package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.dtos.StreamDTO;
import com.sparta.spartansapi.mongodb.models.Stream;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StreamService {

    @Autowired
    private StreamRepository streamRepository;

    public StreamService(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    public List<StreamDTO> findAll() {
        return streamRepository.findAll()
                .stream()
                .map(this::toStreamDTO)
                .collect(Collectors.toList());
    }

    private StreamDTO toStreamDTO(Stream stream) {
        StreamDTO streamDTO = new StreamDTO();
        streamDTO.setStreamname(stream.getStreamname());
        streamDTO.setStreamduration(stream.getStreamduration());
        return streamDTO;
    }

    public ResponseEntity<Stream> updateStream(String id, Stream stream){
        Optional<Stream> streamData = streamRepository.findById(id);
        if (streamData.isPresent()){
            Stream _stream = streamData.get();
            _stream.setStreamname(stream.getStreamname());
            _stream.setStreamduration(stream.getStreamduration());
            return new ResponseEntity<>(streamRepository.save(_stream), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<HttpStatus> deleteById(String id) {
        try {
            streamRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Stream>> findByName(String name){
        try {
            List<Stream> streams = streamRepository.getStreamByStreamnameContains(name);
            if (streams.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(streams, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Stream> addCourse(Stream stream) {
        try {
            return new ResponseEntity<>(streamRepository.insert(stream), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
