package com.gabrieloak.cleanjava.features.user.usecases.createUser;

import com.gabrieloak.cleanjava.features.user.dtos.CreateUser;
import com.gabrieloak.cleanjava.features.user.dtos.ReturnUser;
import com.gabrieloak.cleanjava.utils.errors.BaseError;

import io.jbock.util.Either;

public interface ICreateUserUsecase {
  Either<BaseError, ReturnUser> execute(CreateUser payload);
}
