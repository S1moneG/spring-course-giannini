package com.tasgroup.spring_course.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {

    @Schema(description = "username", example = "Luigi")
    @NotBlank(message = "Username deve essere valorizzato")
    @Size(min = 3, max = 35, message = "Numero di caratteri compreso tra 3-35")
    private String username;

    @Schema(description = "password da associare allo user", examples = "Luigi01!")
    @NotBlank(message = "Password deve essere valorizzato")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "la passwprd deve includere una lettera minuscola, una maiuscola, un numero e un carattere speciale"
    )
    private String password;

    @Schema(description = "Ruolo dello user", examples = "USER")
    @NotBlank(message = "Role deve essere valorizzato")
    @Pattern(regexp = "USER|ADMIN", message = "Solo USER e ADMIN sono ammessi")
    private String role;

    @Schema(description = "Paese di Residenza dello user", examples = "Italia")
    private String paeseResidenza;

    @Schema(description = "Citta di residenza dello user", examples = "Roma")
    private String cittaResidenza;

    @Schema(description = "Data di nascita dello user", examples = "01-01-2000")
    private LocalDate dataDiNascita;

}
