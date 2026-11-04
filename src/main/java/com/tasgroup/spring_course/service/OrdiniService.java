package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.controller.model.CreateOrdineRequest;
import com.tasgroup.spring_course.controller.model.CreateOrdineResponse;
import com.tasgroup.spring_course.exception.MyAppException;
import com.tasgroup.spring_course.repository.OrdiniRepository;
import com.tasgroup.spring_course.repository.model.OrdineEntity;
import com.tasgroup.spring_course.service.mapper.CreateOrdineMapper;
import com.tasgroup.spring_course.utils.enumerations.OrdineStato;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdiniService {

    private final CreateOrdineMapper createOrdineMapper;
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
}
