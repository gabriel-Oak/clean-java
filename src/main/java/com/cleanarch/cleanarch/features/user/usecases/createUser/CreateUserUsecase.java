package com.cleanarch.cleanarch.features.user.usecases.createUser;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.cleanarch.cleanarch.errors.BaseError;
import com.cleanarch.cleanarch.errors.DatasourceError;
import com.cleanarch.cleanarch.errors.InvalidPayloadError;
import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.cleanarch.cleanarch.features.user.dtos.CreateUser;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.features.user.entities.User;

import io.jbock.util.Either;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserUsecase implements ICreateUserUsecase {
  private final IUserInternalDatasource userDatasource;
  private final PasswordEncoder encoder;

  @Override
  public Either<BaseError, ReturnUser> execute(CreateUser payload) {
    if (payload.name() == null || payload.name().length() < 2)
      return Either.left(new InvalidPayloadError("Nome não informado"));

    if (payload.email() == null || payload.email().length() < 2)
      return Either.left(new InvalidPayloadError("Email não informado"));

    if (payload.password() == null || payload.password().length() < 2)
      return Either.left(new InvalidPayloadError("Senha não informada"));

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
