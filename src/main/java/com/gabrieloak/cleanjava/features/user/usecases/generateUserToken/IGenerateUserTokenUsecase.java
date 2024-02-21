package com.gabrieloak.cleanjava.features.user.usecases.generateUserToken;

import com.gabrieloak.cleanjava.features.user.dtos.ReturnUser;
import com.gabrieloak.cleanjava.utils.errors.BaseError;

import io.jbock.util.Either;

public interface IGenerateUserTokenUsecase {
  Either<BaseError, String> execute(ReturnUser user);
}
