package com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.errors;

import com.cleanarch.cleanarch.errors.DatasourceError;

public class UserInternalDatasourceNotFoundError extends DatasourceError {
  public UserInternalDatasourceNotFoundError() {
    super("Oops, não conseguimos encontrar esse usuário");
  }
}
