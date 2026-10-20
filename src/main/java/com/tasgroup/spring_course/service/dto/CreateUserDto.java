package com.tasgroup.spring_course.service.dto;

import lombok.Data;

@Data
public class CreateUserDto {

    private String username;

    private String password;

    private String role;

    private String paeseResidenza;

}
