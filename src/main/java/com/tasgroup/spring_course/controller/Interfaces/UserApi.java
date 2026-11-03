package com.tasgroup.spring_course.controller.Interfaces;

import com.tasgroup.spring_course.controller.model.GetUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

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
                                    )
                            )
                    )
            }
    )
    ResponseEntity<GetUserResponse> getUser(@PathVariable String username);
}
