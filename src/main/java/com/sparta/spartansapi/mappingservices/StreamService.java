package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.dtos.StreamDTO;
import com.sparta.spartansapi.mongodb.models.Stream;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
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
        streamDTO.setStream_name(stream.getStream_name());
        streamDTO.setStream_duration(stream.getStream_duration());
        return streamDTO;
    }


    public ResponseEntity<Stream> updateStream(String id, Stream stream){
        Optional<Stream> streamData = streamRepository.findById(id);
        if (streamData.isPresent()){
            Stream _stream = streamData.get();
            _stream.setStream_name(stream.getStream_name());
            _stream.setStream_duration(stream.getStream_duration());
            return new ResponseEntity<>(streamRepository.save(_stream), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
