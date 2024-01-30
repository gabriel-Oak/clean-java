package com.cleanarch.cleanarch.errors;


public class DatasourceError extends BaseError {
  public DatasourceError(String message) {
    super(message != null ? message : "Tivemos um problema com um dos nossos serviços");
  }
}
