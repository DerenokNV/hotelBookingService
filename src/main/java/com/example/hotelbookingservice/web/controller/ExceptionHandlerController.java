package com.example.hotelbookingservice.web.controller;

import com.example.hotelbookingservice.web.dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse notFound( EntityNotFoundException ex) {
    log.error( "GlobalExceptionHandler. Error when trying to get entity (Ошибка при попытке получить сущность): ", ex );
    return new ErrorResponse( ex.getLocalizedMessage() );
  }

  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse notFound( ValidationException ex) {
    log.error( "GlobalExceptionHandler. ValidationException - Error when trying to get entity (Ошибка при попытке получить сущность): ", ex );
    return new ErrorResponse( ex.getLocalizedMessage() );
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleConstraintViolationException( HttpServletRequest request, Exception ex) {
    log.error( "GlobalExceptionHandler. ConstraintViolationException: ", ex );
    return new ErrorResponse( ex.getLocalizedMessage() );
  }

  @ExceptionHandler(ResponseStatusException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse notFound( ResponseStatusException ex) {
    log.error( "GlobalExceptionHandler. ResponseStatusException - Error when trying to get entity (Ошибка при попытке получить сущность): ", ex );
    return new ErrorResponse( ex.getLocalizedMessage() );
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse notFound( HttpRequestMethodNotSupportedException ex) {
    log.error( "GlobalExceptionHandler. HttpRequestMethodNotSupportedException: ", ex );
    return new ErrorResponse( ex.getLocalizedMessage() + ": Забыли указать Id объекта" );
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse notValid( MethodArgumentNotValidException ex ) {
    log.error( "GlobalExceptionHandler. notValid: ", ex );
    BindingResult bindingResult = ex.getBindingResult();
    List<String> errorMessages = bindingResult.getAllErrors().stream()
            .map( DefaultMessageSourceResolvable::getDefaultMessage )
            .toList();

    String errorMessage = String.join( ";", errorMessages );

    return new ErrorResponse( errorMessage );
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse invocationTarget( NullPointerException ex) {
    log.error( "GlobalExceptionHandler. NPE: ", ex );
    return new ErrorResponse( ex.getLocalizedMessage() );
  }

}
