package com.cleanarch.cleanarch.features.user.usecases.authUser;

import com.cleanarch.cleanarch.features.user.dtos.AuthPayload;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.utils.errors.BaseError;

import io.jbock.util.Either;

public interface IAuthUserUsecase {
  Either<BaseError, ReturnUser> execute(AuthPayload payload);
}
