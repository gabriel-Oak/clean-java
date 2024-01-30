package com.cleanarch.cleanarch.errors;

public class BaseError extends Exception {
  public BaseError(String message) {
    super(message);
  }

  public HttpError toBody() {
    return new HttpError(getMessage());
  }
}
