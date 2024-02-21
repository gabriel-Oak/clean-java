package com.gabrieloak.cleanjava.features.user.usecases.authUser;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.gabrieloak.cleanjava.features.user.dtos.AuthPayload;
import com.gabrieloak.cleanjava.features.user.dtos.AuthUser;
import com.gabrieloak.cleanjava.features.user.dtos.ReturnUser;
import com.gabrieloak.cleanjava.features.user.usecases.authUser.errors.UnauthorizedError;
import com.gabrieloak.cleanjava.utils.errors.BaseError;

import io.jbock.util.Either;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthUserUsecase implements IAuthUserUsecase {
  private AuthenticationManager authenticationManager;

  @Override
  public Either<BaseError, ReturnUser> execute(AuthPayload payload) {
    final var usernamePassword = new UsernamePasswordAuthenticationToken(
        payload.email(),
        payload.password());
    final Authentication auth = authenticationManager.authenticate(usernamePassword);
    if (!auth.isAuthenticated())
      return Either.left(new UnauthorizedError());

    final AuthUser user = (AuthUser) auth.getPrincipal();
    return Either.right(new ReturnUser(
        user.getId(),
        user.getName(),
        user.getEmail()));
  }

}
