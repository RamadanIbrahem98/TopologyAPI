package io.RamadanIbrahem98.TopologyAPI.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<ExceptionResponse> badRequestException(BadRequestException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
            request.getDescription(false), HttpStatus.BAD_REQUEST.getReasonPhrase());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InternalServerError.class)
  public final ResponseEntity<ExceptionResponse> internalServerErrorException(InternalServerError ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
            request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
