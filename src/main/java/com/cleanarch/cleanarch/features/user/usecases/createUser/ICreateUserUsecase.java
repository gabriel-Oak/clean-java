package com.cleanarch.cleanarch.features.user.usecases.createUser;

import com.cleanarch.cleanarch.features.user.dtos.CreateUser;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.utils.errors.BaseError;

import io.jbock.util.Either;

public interface ICreateUserUsecase {
  Either<BaseError, ReturnUser> execute(CreateUser payload);
}
