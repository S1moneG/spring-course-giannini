package com.tasgroup.spring_course.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {

    private String username;

    private String password;

    private String role;

    private String paeseResidenza;

    private String cittaResidenza;

    private LocalDate dataDiNascita;

}
