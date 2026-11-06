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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdiniService {

    private final CreateOrdineMapper createOrdineMapper;
    private final GetOrdineMapper getOrdineMapper;
    private final OrdiniRepository ordiniRepository;

    public CreateOrdineResponse createOrdine(@Valid CreateOrdineRequest createOrdineRequest) {
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

        return createOrdineMapper.createOrdineResponseMapper(savedOrdineEntity);
    }

    public GetOrdineResponse getOrdine(Long id) {
        OrdineEntity ordineEntity = ordiniRepository.findById(id).orElseThrow(()-> new MyAppException("Ordine con id: " + id + " non esiste", HttpStatus.NOT_FOUND));

        return getOrdineMapper.getOrdineResponseMapper(ordineEntity);
    }

    public List<GetOrdineResponse> getOrdini(OrdineStato stato) {

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

        return ordiniResponse;
    }

    public GetOrdineResponse patchOrdineStato(Long id, String newStatoString) {
        OrdineStato newStato = null;
        try {
            if (!OrdineStato.valueOf(newStatoString).equals(OrdineStato.RICEVUTO)){
                newStato = OrdineStato.valueOf(newStatoString);
            } else {
                throw new MyAppException("Il valore: '" + newStatoString + "' non è tra quelli previsti", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new MyAppException("Il valore: '" + newStatoString + "' non è tra quelli previsti", HttpStatus.BAD_REQUEST);
        }

        OrdineEntity ordineEntity = ordiniRepository.findById(id).orElseThrow(()-> new MyAppException("Ordine con id: " + id + " non esiste", HttpStatus.NOT_FOUND));

        switch (ordineEntity.getStato()){
            case RICEVUTO -> {
                if(newStato.equals(OrdineStato.IN_LAVORAZIONE) || newStato.equals(OrdineStato.ANNULLATO)) {
                    return executeUpdateOrdineStato(ordineEntity, newStato, id);
                } else {
                    throw new MyAppException("Transizione non consentita", HttpStatus.CONFLICT);
                }
            }
            case IN_LAVORAZIONE -> {
                if(newStato.equals(OrdineStato.SPEDITO) || newStato.equals(OrdineStato.ANNULLATO)) {
                    return executeUpdateOrdineStato(ordineEntity, newStato, id);
                } else {
                    throw new MyAppException("Transizione non consentita", HttpStatus.CONFLICT);
                }
            }
            case SPEDITO, ANNULLATO -> throw new MyAppException("Transizione non consentita", HttpStatus.CONFLICT);

            case null, default -> throw new MyAppException("Dati sporchi nel db", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public GetOrdineResponse executeUpdateOrdineStato(OrdineEntity ordineEntity, OrdineStato newStato, Long id) {
        ordineEntity.setStato(newStato);
        ordiniRepository.save(ordineEntity);
        OrdineEntity updatedEntity = ordiniRepository.findById(id).orElseThrow(() -> new MyAppException("Ordine con id: " + id + " non trovato in seguito al update", HttpStatus.NOT_FOUND));

        return getOrdineMapper.getOrdineResponseMapper(updatedEntity);
    }

    public List<OrdineEntity.OrdineStatoCountProjection> getStatisticheOrdineByStato() {
        return ordiniRepository.countOrdiniByStato();
    }
}
