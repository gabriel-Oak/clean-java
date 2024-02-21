package com.gabrieloak.cleanjava.features.user.usecases.authUser;

import com.gabrieloak.cleanjava.features.user.dtos.AuthPayload;
import com.gabrieloak.cleanjava.features.user.dtos.ReturnUser;
import com.gabrieloak.cleanjava.utils.errors.BaseError;

import io.jbock.util.Either;

public interface IAuthUserUsecase {
  Either<BaseError, ReturnUser> execute(AuthPayload payload);
}
