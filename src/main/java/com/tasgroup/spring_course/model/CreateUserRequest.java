package com.tasgroup.spring_course.model;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String username;

    private String password;

    private String role;

    private String paeseResidenza;

}
