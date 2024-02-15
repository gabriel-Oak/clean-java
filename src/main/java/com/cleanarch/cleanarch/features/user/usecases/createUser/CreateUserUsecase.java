package com.cleanarch.cleanarch.features.user.usecases.createUser;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.cleanarch.cleanarch.features.user.dtos.CreateUser;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.features.user.entities.User;
import com.cleanarch.cleanarch.utils.errors.BaseError;
import com.cleanarch.cleanarch.utils.errors.DatasourceError;
import com.cleanarch.cleanarch.utils.errors.InvalidPayloadError;

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
