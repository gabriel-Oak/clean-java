package com.cleanarch.cleanarch.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
    Map<String, String> errorMap = new HashMap<>();
    exception.getBindingResult()
        .getFieldErrors()
        .forEach(error -> {
          errorMap.put("message", error.getDefaultMessage());
        });
    return errorMap;
  }
}
