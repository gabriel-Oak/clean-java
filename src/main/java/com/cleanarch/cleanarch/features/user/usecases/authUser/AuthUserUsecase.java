package com.cleanarch.cleanarch.features.user.usecases.authUser;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.cleanarch.cleanarch.features.user.dtos.AuthPayload;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.features.user.usecases.authUser.errors.UnauthorizedError;
import com.cleanarch.cleanarch.utils.errors.BaseError;
import com.cleanarch.cleanarch.utils.errors.DatasourceError;

import io.jbock.util.Either;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthUserUsecase implements IAuthUserUsecase {
  private AuthenticationManager authenticationManager;
  private IUserInternalDatasource userInternalDatasource;

  @Override
  public Either<BaseError, ReturnUser> execute(AuthPayload payload) {
    final var usernamePassword = new UsernamePasswordAuthenticationToken(
        payload.email(),
        payload.password());
    final Authentication auth = authenticationManager.authenticate(usernamePassword);
    if (!auth.isAuthenticated())
      return Either.left(new UnauthorizedError());

    final Either<DatasourceError, ReturnUser> userResult = userInternalDatasource.findUserByEmail(payload.email());
    if (userResult.isLeft())
      return Either.left(userResult.getLeft().get());

    return Either.right(userResult.getRight().get());
  }

}
