package com.tasgroup.spring_course.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetUserRequestDto {

    private String username;

    private LocalDate dataCreazione;

    private String role;

    private String paeseResidenza;

}
