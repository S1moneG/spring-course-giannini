package com.tasgroup.spring_course.controller.advice;

import com.tasgroup.spring_course.controller.advice.model.ApiErrorResponse;
import com.tasgroup.spring_course.exception.MyAppException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyAppException.class)
    public ResponseEntity<ApiErrorResponse> handleAppNotFoundException(MyAppException myAppException, HttpServletRequest request) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(myAppException.getCode());
        apiErrorResponse.setPath(request.getRequestURI());
        apiErrorResponse.setError(myAppException.getCode().getReasonPhrase());
        apiErrorResponse.setMessage(myAppException.getMessage());

        return ResponseEntity.status(myAppException.getCode()).body(apiErrorResponse);
    }
}
