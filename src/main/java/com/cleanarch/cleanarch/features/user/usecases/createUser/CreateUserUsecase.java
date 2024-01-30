package com.cleanarch.cleanarch.features.user.usecases.createUser;

import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.cleanarch.cleanarch.features.user.dtos.CreateUser;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.features.user.entities.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserUsecase implements ICreateUserUsecase {
  private final IUserInternalDatasource userDatasource;

  @Override
  public ReturnUser execute(CreateUser payload) throws Exception {
    if (payload.name() == null || payload.name().length() < 2)
      throw new Exception("Nome não informado");

    if (payload.email() == null || payload.email().length() < 2)
      throw new Exception("Email não informado");

    if (payload.password() == null || payload.password().length() < 2)
      throw new Exception("Senha não informada");

    final User newUser = User.builder()
      .name(payload.name())
      .email(payload.email())
      .password(payload.password())
      .build();

    return userDatasource.saveUser(newUser);
  }
}
