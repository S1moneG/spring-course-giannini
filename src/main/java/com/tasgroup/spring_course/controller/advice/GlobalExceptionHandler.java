package com.tasgroup.spring_course.controller.advice;

import com.tasgroup.spring_course.controller.advice.model.ApiErrorResponse;
import com.tasgroup.spring_course.exception.MyAppException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyAppException.class)
    public ResponseEntity<ApiErrorResponse> handleAppNotFoundException(MyAppException e, HttpServletRequest request) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(e.getCode());
        apiErrorResponse.setPath(request.getRequestURI());
        apiErrorResponse.setError(e.getCode().getReasonPhrase());
        apiErrorResponse.setMessage(e.getMessage());

        return ResponseEntity.status(e.getCode()).body(apiErrorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleAppNotFoundException(MethodArgumentNotValidException e, HttpServletRequest request) {

        String message = e.getBindingResult().getFieldErrors().
                stream().
                map(DefaultMessageSourceResolvable::getDefaultMessage).
                collect(Collectors.joining(", "));
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
        apiErrorResponse.setPath(request.getRequestURI());
        apiErrorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        apiErrorResponse.setMessage(message);

        return ResponseEntity.badRequest().body(apiErrorResponse);
    }
}
