package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.dtos.StreamDTO;
import com.sparta.spartansapi.mongodb.models.Stream;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
