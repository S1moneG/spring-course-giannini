package com.tasgroup.spring_course.controller;

import com.tasgroup.spring_course.controller.Interfaces.OrdiniApi;
import com.tasgroup.spring_course.controller.model.CreateOrdineRequest;
import com.tasgroup.spring_course.controller.model.CreateOrdineResponse;
import com.tasgroup.spring_course.service.OrdiniService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Ordini Controller", description = "Controller con endpoint dedicati agli ordini")
public class OrdiniController implements OrdiniApi {

    @Autowired
    private final OrdiniService ordiniService;

    @Override
    @PostMapping("/ordini")
    public ResponseEntity<CreateOrdineResponse> postOrdine(@Valid @RequestBody CreateOrdineRequest createOrdineRequest) {
        return ordiniService.createOrdine(createOrdineRequest);
    }
}
