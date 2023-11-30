package com.sparta.jarjarbinks.worldproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> NotFoundHandler(NotFoundException e) {
        NotFoundResponse response = new NotFoundResponse(e.getMessage(), 400);
        return ResponseEntity.badRequest().body(String.valueOf(response));
    }
}
