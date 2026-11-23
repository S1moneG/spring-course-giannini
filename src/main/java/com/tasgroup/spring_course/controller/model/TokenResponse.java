package com.tasgroup.spring_course.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;
    private String tokenType;
    private Long expiresIn;

}
