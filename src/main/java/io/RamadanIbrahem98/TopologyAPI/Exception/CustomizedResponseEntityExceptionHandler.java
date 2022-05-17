package io.RamadanIbrahem98.TopologyAPI.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
  @ExceptionHandler(TopologyNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleNotFoundException(TopologyNotFoundException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
            request.getDescription(false), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<ExceptionResponse> badRequestException(BadRequestException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
            request.getDescription(false), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
  }
}
