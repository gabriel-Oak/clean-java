package com.gabrieloak.cleanjava.features.user.usecases.authUser.errors;

import com.gabrieloak.cleanjava.utils.errors.BaseError;

public class UnauthorizedError extends BaseError {
  public UnauthorizedError() {
    super("Acesso não autorizado");
  }
}
