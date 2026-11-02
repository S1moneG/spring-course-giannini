package com.tasgroup.spring_course.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Username deve essere valorizzato")
    @Size(min = 3, max = 35, message = "Numero di caratteri compreso tra 3-35")
    private String username;

    @NotBlank(message = "Password deve essere valorizzato")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "la passwprd deve includere una lettera minuscola, una maiuscola, un numero e un carattere speciale"
    )
    private String password;

    @NotBlank(message = "Role deve essere valorizzato")
    @Pattern(regexp = "USER|ADMIN", message = "Solo USER e ADMIN sono ammessi")
    private String role;

    private String paeseResidenza;

    private String cittaResidenza;

    private LocalDate dataDiNascita;

}
