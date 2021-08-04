package com.meli.ipvalidator.exception;

import brave.Tracer;
import com.meli.ipvalidator.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CallLegacyExceptionHandler {

    @Autowired
    private Tracer tracer;

    @ExceptionHandler
    public ResponseEntity<?> handlerException(CallLegacyException ex) {
        ResponseDto error = new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null, tracer.currentSpan().context().traceIdString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
