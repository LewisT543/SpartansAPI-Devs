package com.sparta.spartansapi.mappingservices.classes;

import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamMappingService {
    @Autowired
    StreamRepository streamRepository;
}
