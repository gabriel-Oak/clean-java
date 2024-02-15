package com.cleanarch.cleanarch.features.user.usecases.generateUserToken;

import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.utils.errors.BaseError;

import io.jbock.util.Either;

public interface IGenerateUserTokenUsecase {
  Either<BaseError, String> execute(ReturnUser user);
}
