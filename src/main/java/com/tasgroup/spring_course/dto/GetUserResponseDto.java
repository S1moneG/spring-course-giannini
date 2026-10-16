package com.tasgroup.spring_course.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetUserResponseDto {

    private String username;

    private LocalDate dataCreazione;

    private String role;

    private String paeseResidenza;

    public GetUserResponseDto() {
    }
}
