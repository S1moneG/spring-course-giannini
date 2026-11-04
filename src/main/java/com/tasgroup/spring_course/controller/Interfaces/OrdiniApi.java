package com.tasgroup.spring_course.controller.Interfaces;

import com.tasgroup.spring_course.controller.model.CreateOrdineRequest;
import com.tasgroup.spring_course.controller.model.CreateOrdineResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
                                                  "daDefinire": "daDefinire"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = CreateOrdineResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<CreateOrdineResponse> postOrdine(@Valid @RequestBody CreateOrdineRequest createOrdineRequest);
}
