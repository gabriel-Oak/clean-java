package com.cleanarch.cleanarch.features.user.usecases.createUser;

import com.cleanarch.cleanarch.features.user.dtos.CreateUser;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;

public interface ICreateUserUsecase {
  ReturnUser execute(CreateUser payload) throws Exception;
}
