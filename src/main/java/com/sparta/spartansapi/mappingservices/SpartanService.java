package com.sparta.spartansapi.mappingservices;

import com.sparta.spartansapi.dtos.SpartanDTO;
import com.sparta.spartansapi.mongodb.models.Spartan;
import com.sparta.spartansapi.mongodb.repos.SpartanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpartanService {

    @Autowired
    private SpartanRepository spartanRepository;

    public SpartanService(SpartanRepository spartanRepository) {
        this.spartanRepository = spartanRepository;
    }

    public List<SpartanDTO> findAll() {
        return spartanRepository.findAll()
                .stream()
                .map(this::toSpartanDTO)
                .collect(Collectors.toList());
    }

    private SpartanDTO toSpartanDTO(Spartan spartan) {
        SpartanDTO spartanDTO = new SpartanDTO();
        spartanDTO.setCourse(spartan.getCourse());
        spartanDTO.setEmail(spartan.getEmail());
        spartanDTO.setEnd_date(spartan.getEnd_date());
        spartanDTO.setFirst_name(spartan.getFirst_name());
        spartanDTO.setLast_name(spartan.getLast_name());
        spartanDTO.setStream(spartan.getStream());
        spartanDTO.setStart_date(spartan.getStart_date());
        spartanDTO.setMiddle_name(spartan.getMiddle_name());
        return spartanDTO;
    }
}
