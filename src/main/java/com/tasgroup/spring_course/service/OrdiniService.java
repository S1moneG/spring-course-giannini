package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.controller.model.CreateOrdineRequest;
import com.tasgroup.spring_course.controller.model.CreateOrdineResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrdiniService {
    public ResponseEntity<CreateOrdineResponse> createOrdine(@Valid CreateOrdineRequest createOrdineRequest) {
        ResponseEntity<CreateOrdineResponse> createOrdineResponse = new ResponseEntity<>(HttpStatus.CREATED);
        return createOrdineResponse;
    }
}
