package com.cleanarch.cleanarch.features.user.usecases.decodeUserToken;

import com.cleanarch.cleanarch.utils.errors.BaseError;

import io.jbock.util.Either;

public interface IDecodeUserTokenUsecase {
  Either<BaseError, String> execute(String token);
}
