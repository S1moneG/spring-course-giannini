package com.tasgroup.spring_course.controller.Interfaces;

import com.tasgroup.spring_course.controller.advice.model.ApiErrorResponse;
import com.tasgroup.spring_course.controller.model.CreateOrdineRequest;
import com.tasgroup.spring_course.controller.model.CreateOrdineResponse;
import com.tasgroup.spring_course.controller.model.GetOrdineResponse;
import com.tasgroup.spring_course.repository.model.OrdineEntity;
import com.tasgroup.spring_course.utils.enumerations.OrdineStato;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Il body della request non è valido",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "error": "Bad Request",
                                                  "message": "Il type del campo: 'id' è errato",
                                                  "path": "/api/ordini/a",
                                                  "status": "400 BAD_REQUEST",
                                                  "timestamp": "2026-11-05T14:23:57.5390224"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<CreateOrdineResponse> postOrdine(@Valid @RequestBody CreateOrdineRequest createOrdineRequest);




    @Operation(summary = "Get Ordine by Id", description = "Endpoint usato per recuperare i dettagli di un ordine tramite id dello stesso")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ordine recuperato correttamente",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                  {
                                                        "cap": "20019",
                                                        "cittaSpedizione": "Milano",
                                                        "codiceOrdine": "4b0893a40d084d648b78651f8f2f19",
                                                        "cognomeCliente": "Giannini",
                                                        "dataOrdine": "2026-11-04T16:06:14.632454",
                                                        "emailCliente": "simo.g@gmail.com",
                                                        "id": 1,
                                                        "indirizzoSpedizione": "Via di Vittorio 35",
                                                        "nomeCliente": "Simone",
                                                        "prezzoUnitario": 2.5,
                                                        "prodotto": "Amoniaca Gel",
                                                        "quantita": 3,
                                                        "stato": "RICEVUTO",
                                                        "telefonoCliente": "3457782345",
                                                        "totale": 7.5
                                                  }
                                            """
                                    ),
                                    schema = @Schema(implementation = GetOrdineResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "campo id non è valido",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "error": "Bad Request",
                                                  "message": "Il type del campo: 'id' è errato",
                                                  "path": "/api/ordini/a",
                                                  "status": "400 BAD_REQUEST",
                                                  "timestamp": "2026-11-05T14:23:57.5390224"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "ordine non trovato",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "error": "Not Found",
                                                  "message": "Ordine con id: 9 non esiste",
                                                  "path": "/api/ordini/9",
                                                  "status": "404 NOT_FOUND",
                                                  "timestamp": "2026-11-05T14:25:43.5384221"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<GetOrdineResponse> getOrdine(@PathVariable Long id);




    @Operation(summary = "Get Ordini", description = "Endpoint usato per recuperare tutti gli ordini, filtro stato opzionale")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ordine creato correttamente",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                [
                                                      {
                                                            "cap": "20019",
                                                            "cittaSpedizione": "Milano",
                                                            "codiceOrdine": "4b0893a40d084d648b78651f8f2f19",
                                                            "cognomeCliente": "Giannini",
                                                            "dataOrdine": "2026-11-04T16:06:14.632454",
                                                            "emailCliente": "simo.g@gmail.com",
                                                            "id": 1,
                                                            "indirizzoSpedizione": "Via di Vittorio 35",
                                                            "nomeCliente": "Simone",
                                                            "prezzoUnitario": 2.5,
                                                            "prodotto": "Amoniaca Gel",
                                                            "quantita": 3,
                                                            "stato": "RICEVUTO",
                                                            "telefonoCliente": "3457782345",
                                                            "totale": 7.5
                                                      },
                                                      {
                                                            "cap": "20019",
                                                            "cittaSpedizione": "Milano",
                                                            "codiceOrdine": "002bf91bfa954fcaae4c42d9213747",
                                                            "cognomeCliente": "Giannini",
                                                            "dataOrdine": "2026-11-04T16:08:22.761407",
                                                            "emailCliente": "simo.g@gmail.com",
                                                            "id": 2,
                                                            "indirizzoSpedizione": "Via di Vittorio 35",
                                                            "nomeCliente": "Simone",
                                                            "prezzoUnitario": 2.5,
                                                            "prodotto": "Amoniaca Gel",
                                                            "quantita": 4,
                                                            "stato": "RICEVUTO",
                                                            "telefonoCliente": null,
                                                            "totale": 10.0
                                                      }
                                                  ]
                                            """
                                    ),
                                    schema = @Schema(type = "array", implementation = GetOrdineResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "il campo stato non ha un valore presente nel ENUM",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "error": "Bad Request",
                                                  "message": "Il campo: 'stato' ha un valore non previsto",
                                                  "path": "/api/ordini",
                                                  "status": "400 BAD_REQUEST",
                                                  "timestamp": "2026-11-05T14:27:10.2882007"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<List<GetOrdineResponse>> getOrdini(
            @Parameter(
                description = "Id del ordine da recuperare",
                example = "1",
                required = true
            )
            @RequestParam OrdineStato stato
    );




    @Operation(summary = "Patch Ordine by Id",
            description =
                    """
                            Endpoint usato per aggiornare stato di un ordine tramite id dello stesso, dettagli sulle transizioni:
                            • RICEVUTO → IN_LAVORAZIONE oppure ANNULLATO.
                            • IN_LAVORAZIONE → SPEDITO oppure ANNULLATO.
                            • SPEDITO e ANNULLATO sono stati finali.
                    """
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ordine aggiornato correttamente",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                  {
                                                        "cap": "20019",
                                                        "cittaSpedizione": "Milano",
                                                        "codiceOrdine": "4b0893a40d084d648b78651f8f2f19",
                                                        "cognomeCliente": "Giannini",
                                                        "dataOrdine": "2026-11-04T16:06:14.632454",
                                                        "emailCliente": "simo.g@gmail.com",
                                                        "id": 1,
                                                        "indirizzoSpedizione": "Via di Vittorio 35",
                                                        "nomeCliente": "Simone",
                                                        "prezzoUnitario": 2.5,
                                                        "prodotto": "Amoniaca Gel",
                                                        "quantita": 3,
                                                        "stato": "ANNULLATO",
                                                        "telefonoCliente": "3457782345",
                                                        "totale": 7.5
                                                  }
                                            """
                                    ),
                                    schema = @Schema(implementation = GetOrdineResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "campo stato non è valido",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "error": "Bad Request",
                                                  "message": "Il campo: 'stato' ha un valore non previsto",
                                                  "path": "/api/ordini/1/stato",
                                                  "status": "400 BAD_REQUEST",
                                                  "timestamp": "2026-11-05T14:23:57.5390224"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "ordine non trovato",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "error": "Not Found",
                                                  "message": "Ordine con id: 9 non esiste",
                                                  "path": "/api/ordini/9/stato",
                                                  "status": "404 NOT_FOUND",
                                                  "timestamp": "2026-11-05T14:25:43.5384221"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflitto tra vecchio e nuovo stato",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "error": "Conflict",
                                                  "message": "Transizione non consentita",
                                                  "path": "/api/ordini/9/stato",
                                                  "status": "409 CONFLICT",
                                                  "timestamp": "2026-11-05T14:25:43.5384221"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Errore non previsto",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "error": "Internal_Error",
                                                  "message": "Dati sporchi nel DB",
                                                  "path": "/api/ordini/9/stato",
                                                  "status": "409 INTERNAL_ERROR",
                                                  "timestamp": "2026-11-05T14:25:43.5384221"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<GetOrdineResponse> patchOrdineStato(
            @Parameter(
                    description = "Id del ordine da aggiornare",
                    example = "1",
                    required = true
            )
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = """
                                Nuovo stato del ordine, valori accettati:
                                -IN_LAVORAZIONE
                                -RICEVUTO
                                -ANNULLATO
                            """,
                    required = true,
                    content = @Content(
                            schema = @Schema(
                                    examples = "ANNULLATO"
                            )
                    )
            )
            @RequestBody String newStato
    );




    @Operation(summary = "Get statistiche by stato", description = "Endpoint usato per recuperare il numero totale di ordini per ogni stato")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Statistiche recuperate",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                [
                                                  {
                                                    "numeroOrdini": 1,
                                                    "stato": "SPEDITO"
                                                  },
                                                  {
                                                    "numeroOrdini": 1,
                                                    "stato": "IN_LAVORAZIONE"
                                                  },
                                                  {
                                                    "numeroOrdini": 2,
                                                    "stato": "ANNULLATO"
                                                  },
                                                  {
                                                    "numeroOrdini": 1,
                                                    "stato": "RICEVUTO"
                                                  }
                                                ]
                                            """
                                    ),
                                    schema = @Schema(implementation = OrdineEntity.OrdineStatoCountProjection.class)
                            )
                    )
            }
    )
    ResponseEntity<List<OrdineEntity.OrdineStatoCountProjection>> getSatisticheOrdineByStato();
}
