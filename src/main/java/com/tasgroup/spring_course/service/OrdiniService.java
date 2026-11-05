package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.controller.model.CreateOrdineRequest;
import com.tasgroup.spring_course.controller.model.CreateOrdineResponse;
import com.tasgroup.spring_course.controller.model.GetOrdineResponse;
import com.tasgroup.spring_course.exception.MyAppException;
import com.tasgroup.spring_course.repository.OrdiniRepository;
import com.tasgroup.spring_course.repository.model.OrdineEntity;
import com.tasgroup.spring_course.service.mapper.CreateOrdineMapper;
import com.tasgroup.spring_course.service.mapper.GetOrdineMapper;
import com.tasgroup.spring_course.utils.enumerations.OrdineStato;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdiniService {

    private final CreateOrdineMapper createOrdineMapper;
    private final GetOrdineMapper getOrdineMapper;
    private final OrdiniRepository ordiniRepository;

    public ResponseEntity<CreateOrdineResponse> createOrdine(@Valid CreateOrdineRequest createOrdineRequest) {
        OrdineEntity ordineEntity = createOrdineMapper.createOrdineRequestMapper(createOrdineRequest);

        String codiceOrdine = UUID.randomUUID().toString().replace("-", "").substring(0,30);

        ordineEntity.setStato(OrdineStato.RICEVUTO);
        ordineEntity.setCodiceOrdine(codiceOrdine);
        ordineEntity.setDataOrdine(LocalDateTime.now());

        ordiniRepository.save(ordineEntity);

        OrdineEntity savedOrdineEntity = ordiniRepository.findByCodiceOrdine(codiceOrdine).
                orElseThrow(()-> new MyAppException(
                        "L'ordine: " + codiceOrdine + " non è stato salvato correttamente",
                        HttpStatus.NOT_FOUND)
                );
        CreateOrdineResponse createOrdineResponse = createOrdineMapper.createOrdineResponseMapper(savedOrdineEntity);

        return new ResponseEntity<>(createOrdineResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<GetOrdineResponse> getOrdine(Long id) {
        OrdineEntity ordineEntity = ordiniRepository.findById(id).orElseThrow(()-> new MyAppException("Ordine con id: " + id + " non esiste", HttpStatus.NOT_FOUND));

        GetOrdineResponse ordineResponse = getOrdineMapper.getOrdineResponseMapper(ordineEntity);

        return new ResponseEntity<>(ordineResponse, HttpStatus.OK);
    }

    public ResponseEntity<List<GetOrdineResponse>> getOrdini(OrdineStato stato) {

        List<OrdineEntity> ordiniEntities;

        if(stato == null) {
            ordiniEntities = ordiniRepository.findAll();
        } else {
            ordiniEntities = ordiniRepository.findByStato(stato);
        }

        List<GetOrdineResponse> ordiniResponse = new ArrayList<>();

        if(!ordiniEntities.isEmpty()) {
            for(OrdineEntity ordineEntity : ordiniEntities) {
                GetOrdineResponse singleOrdineResponse = getOrdineMapper.getOrdineResponseMapper(ordineEntity);
                ordiniResponse.add(singleOrdineResponse);
            }
        }

        return new ResponseEntity<>(ordiniResponse, HttpStatus.OK);
    }
}
