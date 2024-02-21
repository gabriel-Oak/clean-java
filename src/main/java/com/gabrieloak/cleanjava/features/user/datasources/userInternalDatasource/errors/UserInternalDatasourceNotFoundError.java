package com.gabrieloak.cleanjava.features.user.datasources.userInternalDatasource.errors;

import com.gabrieloak.cleanjava.utils.errors.DatasourceError;

public class UserInternalDatasourceNotFoundError extends DatasourceError {
  public UserInternalDatasourceNotFoundError() {
    super("Oops, não conseguimos encontrar esse usuário");
  }
}
