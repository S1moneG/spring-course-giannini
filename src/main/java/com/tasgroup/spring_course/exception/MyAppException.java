package com.tasgroup.spring_course.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MyAppException extends RuntimeException {

    private HttpStatus code;

    public MyAppException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
