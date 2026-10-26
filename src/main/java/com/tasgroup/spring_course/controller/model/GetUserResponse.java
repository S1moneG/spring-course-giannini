package com.tasgroup.spring_course.controller.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetUserResponse {

    private String username;

    private LocalDate dataCreazione;

    private String role;

    private String paeseResidenza;

}
