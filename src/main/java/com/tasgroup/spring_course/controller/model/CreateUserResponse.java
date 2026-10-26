package com.tasgroup.spring_course.controller.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateUserResponse {
    private String username;

    private LocalDateTime dataCreazione;

    private String role;

    private String residenza;

    private LocalDate dataDiNascita;

}
