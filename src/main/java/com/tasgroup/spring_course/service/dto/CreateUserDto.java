package com.tasgroup.spring_course.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserDto {

    private String username;

    private String password;

    private LocalDate dataCreazione;

    private String role;

    private String paeseResidenza;

    private String cittaResidenza;

    private LocalDate dataDiNascita;

    private String residenza;
}
