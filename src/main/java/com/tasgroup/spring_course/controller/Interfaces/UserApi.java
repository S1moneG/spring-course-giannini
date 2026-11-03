package com.tasgroup.spring_course.controller.Interfaces;

import com.tasgroup.spring_course.controller.advice.model.ApiErrorResponse;
import com.tasgroup.spring_course.controller.model.CreateUserRequest;
import com.tasgroup.spring_course.controller.model.CreateUserResponse;
import com.tasgroup.spring_course.controller.model.GetUserResponse;
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

public interface UserApi {

    @Operation(summary = "Get User", description = "Recupera uno user in base allo username")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User recuperato correttamente",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                        {
                                                          "dataCreazione": "2026-11-02",
                                                          "paeseResidenza": "Italy",
                                                          "role": "USER",
                                                          "username": "Simone2"
                                                        }
                                                    """
                                    ),
                                    schema = @Schema(implementation = GetUserResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User non trovato",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                      "error": "Not Found",
                                                      "message": "User: Simone non trovato",
                                                      "path": "/user/Simone",
                                                      "status": "404 NOT_FOUND",
                                                      "timestamp": "2026-11-03T10:33:06.1542159"
                                                    }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<GetUserResponse> getUser(@PathVariable String username);

    @Operation(summary = "Create User", description = "Crea una nuova utenza")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User creato correttamente",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                      "dataCreazione": "2026-11-03T10:47:41.0420215",
                                                      "dataDiNascita": "2001-04-02",
                                                      "residenza": "Italy - Milan",
                                                      "role": "ADMIN",
                                                      "username": "Simone"
                                                    }
                                            """
                                    ),
                                    schema = @Schema(implementation = CreateUserResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errore sulla valorizzazione nei campi della request",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                          "error": "Bad Request",
                                                          "message": "la passwprd deve includere una lettera minuscola, una maiuscola, un numero e un carattere speciale",
                                                          "path": "/user/create",
                                                          "status": "400 BAD_REQUEST",
                                                          "timestamp": "2026-11-03T10:48:08.8105124"
                                                    }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest);

    @Operation(summary = "Delete User", description = "Recupera uno user in base allo username")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User eliminato con successo",
                            content = @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                Rimosso user: Simone4
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User non trovato",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            """
                                                {
                                                  "error": "Not Found",
                                                  "message": "User: Simone5 non esiste",
                                                  "path": "/user/Simone5",
                                                  "status": "404 NOT_FOUND",
                                                  "timestamp": "2026-11-03T11:29:40.7697717"
                                                }
                                            """
                                    ),
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<String> deleteUser(@PathVariable String username);
}
