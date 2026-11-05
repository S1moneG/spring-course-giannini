package com.tasgroup.spring_course.controller.advice;

import com.tasgroup.spring_course.controller.advice.model.ApiErrorResponse;
import com.tasgroup.spring_course.exception.MyAppException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tools.jackson.databind.exc.UnrecognizedPropertyException;

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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e, HttpServletRequest request) {

        String message;
        if(e.getCause() instanceof UnrecognizedPropertyException cause) {
            String campo = cause.getPropertyName();
            message = "Campo: '" + campo + "' non è previsto";
        } else {
            message = "Json non valido";
        }

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(message);
        apiErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
        apiErrorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        apiErrorResponse.setPath(request.getRequestURI());
        apiErrorResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.badRequest().body(apiErrorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e, HttpServletRequest request) {

        Class<?> requiredType = e.getRequiredType();

        String message;
        if(requiredType != null && requiredType.isEnum()) {
            message = "Il campo: '" + e.getPropertyName() + "' ha un valore non previsto";
        } else {
            message = "Il type del campo: '" + e.getPropertyName() + "' è errato";
        }

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(message);
        apiErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
        apiErrorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        apiErrorResponse.setPath(request.getRequestURI());
        apiErrorResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.badRequest().body(apiErrorResponse);
    }
}
