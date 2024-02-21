package com.gabrieloak.cleanjava.features.user.usecases.decodeUserToken;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gabrieloak.cleanjava.utils.errors.BaseError;

import io.jbock.util.Either;

public class DecodeUserTokenUsecase implements IDecodeUserTokenUsecase {
  @Value("api.security.token.secret")
  private String secret;

  @Override
  public Either<BaseError, String> execute(String token) {
    try {
      final Algorithm algorithm = Algorithm.HMAC256(secret);
      final String subject = JWT.require(algorithm)
          .withIssuer("auth-api")
          .build()
          .verify(token)
          .getSubject();
      return Either.right(subject);
    } catch (JWTVerificationException e) {
      return Either.left(new BaseError(e.getMessage()));
    }
  }

}
