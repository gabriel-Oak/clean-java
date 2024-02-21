package com.gabrieloak.cleanjava.features.user.usecases.createUser;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.gabrieloak.cleanjava.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.gabrieloak.cleanjava.features.user.dtos.CreateUser;
import com.gabrieloak.cleanjava.features.user.dtos.ReturnUser;
import com.gabrieloak.cleanjava.features.user.entities.User;
import com.gabrieloak.cleanjava.utils.errors.BaseError;
import com.gabrieloak.cleanjava.utils.errors.DatasourceError;
import com.gabrieloak.cleanjava.utils.errors.InvalidPayloadError;

import io.jbock.util.Either;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserUsecase implements ICreateUserUsecase {
  private final IUserInternalDatasource userDatasource;
  private final PasswordEncoder encoder;

  @Override
  public Either<BaseError, ReturnUser> execute(CreateUser payload) {
    final Either<DatasourceError, ReturnUser> userResult = userDatasource
        .findUserByEmail(payload.email());
    if (userResult.isRight())
      return Either.left(new InvalidPayloadError("Um Usuário com esse email já existe"));

    final User newUser = User.builder()
        .name(payload.name())
        .email(payload.email())
        .password(encoder.encode(payload.password()))
        .build();

    return Either.right(userDatasource.saveUser(newUser));
  }
}
