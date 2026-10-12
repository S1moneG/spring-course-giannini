package com.tasgroup.spring_course.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDto {

    private String username;

    private String password;

    private String role;

    private String paeseResidenza;

    public CreateUserRequestDto() {
    }
}
