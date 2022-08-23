package com.example.notificationService.NotificationService.exception.handler;

import com.example.notificationService.NotificationService.constant.ErrorConstants;
import com.example.notificationService.NotificationService.exception.InvalidRequestException;
import com.example.notificationService.NotificationService.exception.NotFoundException;
import com.example.notificationService.NotificationService.response.ErrorResponse;
import com.example.notificationService.NotificationService.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception ex) {
        log.error(ex.getLocalizedMessage(), ex);
        GenericResponse response = new GenericResponse();
        ErrorResponse error = new ErrorResponse();
        error.setCode(ErrorConstants.INTERNAL_SERVER_ERROR);
        error.setMessage(ex.getLocalizedMessage());
        response.setError(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity handleInValidRequestException(InvalidRequestException ex)
    {
        GenericResponse response = new GenericResponse();
        ErrorResponse error = new ErrorResponse();
        error.setCode(ErrorConstants.INVALID_REQUEST_ERROR);
        error.setMessage(ex.getMessage());
        response.setError(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException ex)
    {
        GenericResponse response = new GenericResponse();
        ErrorResponse error = new ErrorResponse();
        error.setCode(ErrorConstants.INVALID_REQUEST_ERROR);
        error.setMessage(ex.getMessage());
        response.setError(error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GenericResponse response = new GenericResponse();
        ErrorResponse error = new ErrorResponse();
        error.setCode(ErrorConstants.INVALID_REQUEST_ERROR);
        error.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        response.setError(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
