package com.meli.ipvalidator.exception;

import brave.Tracer;
import com.meli.ipvalidator.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadRequestExceptionHandler {

    @Autowired
    private Tracer tracer;

    @ExceptionHandler
    public ResponseEntity<?> exceptionHandler(BadRequestException ex) {
        ResponseDto error = new ResponseDto(HttpStatus.BAD_REQUEST, ex.getMessage(), null, tracer.currentSpan().context().traceIdString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
