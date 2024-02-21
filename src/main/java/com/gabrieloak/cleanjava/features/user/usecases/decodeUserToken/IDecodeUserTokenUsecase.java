package com.gabrieloak.cleanjava.features.user.usecases.decodeUserToken;

import com.gabrieloak.cleanjava.utils.errors.BaseError;

import io.jbock.util.Either;

public interface IDecodeUserTokenUsecase {
  Either<BaseError, String> execute(String token);
}
