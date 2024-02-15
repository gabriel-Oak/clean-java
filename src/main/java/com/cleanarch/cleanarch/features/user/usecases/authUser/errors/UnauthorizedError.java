package com.cleanarch.cleanarch.features.user.usecases.authUser.errors;

import com.cleanarch.cleanarch.utils.errors.BaseError;

public class UnauthorizedError extends BaseError {
  public UnauthorizedError() {
    super("Acesso não autorizado");
  }
}
