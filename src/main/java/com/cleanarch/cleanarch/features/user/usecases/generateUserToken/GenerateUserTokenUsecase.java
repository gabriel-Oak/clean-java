package com.cleanarch.cleanarch.features.user.usecases.generateUserToken;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.utils.errors.BaseError;

import io.jbock.util.Either;

public class GenerateUserTokenUsecase implements IGenerateUserTokenUsecase {
  @Value("api.security.token.secret")
  private String secret;

  @Override
  public Either<BaseError, String> execute(ReturnUser user) {
    try {
      final Algorithm algorithm = Algorithm.HMAC256(secret);
      final String token = JWT.create()
          .withIssuer("auth-api")
          .withSubject(user.email())
          .withExpiresAt(getExpiration())
          .sign(algorithm);

      return Either.right(token);
    } catch (Exception e) {
      return Either.left(new BaseError(e.getMessage()));
    }
  }

  private Instant getExpiration() {
    return LocalDateTime.now()
        .plusHours(6)
        .toInstant(ZoneOffset.of("-03:00"));
  }
}
