package com.tasgroup.spring_course.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserResponse {
    private String username;

    private LocalDate dataCreazione;

    private String role;

    private String residenza;

    private LocalDate dataDiNascita;

}
