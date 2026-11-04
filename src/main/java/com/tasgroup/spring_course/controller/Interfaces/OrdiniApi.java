package com.tasgroup.spring_course.controller.Interfaces;

import com.tasgroup.spring_course.controller.model.CreateOrdineRequest;
import com.tasgroup.spring_course.controller.model.CreateOrdineResponse;
import com.tasgroup.spring_course.controller.model.GetOrdineResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrdiniApi {

    @Operation(summary = "Post Ordine", description = "Endpoint usato per la creazione di un ordine")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ordine creato correttamente",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "cap": "20019",
                                                  "cittaSpedizione": "Milano",
                                                  "codiceOrdine": "b14fa9847cf44426be1988112e7656",
                                                  "cognomeCliente": "Giannini",
                                                  "dataOrdine": "2026-11-04T16:44:55.1605279",
                                                  "emailCliente": "simo.g@gmail.com",
                                                  "id": 5,
                                                  "indirizzoSpedizione": "Via di Vittorio 35",
                                                  "nomeCliente": "Simone",
                                                  "prezzoUnitario": 2.5,
                                                  "prodotto": "Amoniaca Gel",
                                                  "quantita": 4,
                                                  "stato": "RICEVUTO",
                                                  "telefonoCliente": null,
                                                  "totale": 10.0
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = CreateOrdineResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<CreateOrdineResponse> postOrdine(@Valid @RequestBody CreateOrdineRequest createOrdineRequest);

    ResponseEntity<GetOrdineResponse> getOrdine(@PathVariable Long id);
}
