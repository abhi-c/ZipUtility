package com.guy.carpenter.zipper.zipdecoder.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.guy.carpenter.zipper.zipdecoder.exception.ZipUtilException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value 
      = { IllegalArgumentException.class, ZipUtilException.class, IOException.class })
    protected ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Request could not be completed either because your input is incorrect or data is not availabel. Detailed message: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }
}