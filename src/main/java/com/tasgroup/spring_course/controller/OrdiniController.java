package com.tasgroup.spring_course.controller;

import com.tasgroup.spring_course.controller.Interfaces.OrdiniApi;
import com.tasgroup.spring_course.controller.model.CreateOrdineRequest;
import com.tasgroup.spring_course.controller.model.CreateOrdineResponse;
import com.tasgroup.spring_course.controller.model.GetOrdineResponse;
import com.tasgroup.spring_course.service.OrdiniService;
import com.tasgroup.spring_course.utils.enumerations.OrdineStato;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Override
    @GetMapping("/ordini/{id}")
    public ResponseEntity<GetOrdineResponse> getOrdine(@PathVariable Long id) {
        return ordiniService.getOrdine(id);
    }

    @Override
    @GetMapping("/ordini")
    public ResponseEntity<List<GetOrdineResponse>> getOrdini(@RequestParam(required = false) OrdineStato stato) {
        return ordiniService.getOrdini(stato);
    }

    @Override
    @PatchMapping("/ordini/{id}/stato")
    public ResponseEntity<GetOrdineResponse> patchOrdineStato(@PathVariable Long id, @RequestBody String newStato) {
        return ordiniService.patchOrdineStato(id, newStato);
    }
}
