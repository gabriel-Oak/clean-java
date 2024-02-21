package com.gabrieloak.cleanjava.features.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrieloak.cleanjava.features.user.dtos.AuthPayload;
import com.gabrieloak.cleanjava.features.user.dtos.CreateUser;
import com.gabrieloak.cleanjava.features.user.dtos.ReturnAuth;
import com.gabrieloak.cleanjava.features.user.dtos.ReturnUser;
import com.gabrieloak.cleanjava.features.user.usecases.authUser.IAuthUserUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.createUser.ICreateUserUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.generateUserToken.IGenerateUserTokenUsecase;
import com.gabrieloak.cleanjava.utils.errors.BaseError;
import com.gabrieloak.cleanjava.utils.errors.InvalidPayloadError;

import io.jbock.util.Either;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController()
@RequestMapping("/user")
public class UserController {
  final private ICreateUserUsecase createUserUsecase;
  final private IAuthUserUsecase authUserUsecase;
  final private IGenerateUserTokenUsecase generateUserTokenUsecase;

  @PostMapping("/auth")
  Object auth(@Valid @RequestBody AuthPayload payload) {
    final Either<BaseError, ReturnUser> authResult = authUserUsecase.execute(payload);
    if (authResult.isLeft()) {
      final BaseError error = authResult.getLeft().get();
      int status = 500;
      return ResponseEntity.status(status).body(error.toBody());
    }

    final ReturnUser user = authResult.getRight().get();
    final Either<BaseError, String> tokenResult = generateUserTokenUsecase.execute(user);
    if (tokenResult.isLeft()) {
      return ResponseEntity
          .status(500)
          .body(tokenResult.getLeft().get().toBody());
    }

    return new ReturnAuth(user, tokenResult.getRight().get());
  }

  @PostMapping
  Object createUser(@Valid @RequestBody CreateUser payload) {
    final Either<BaseError, ReturnUser> result = createUserUsecase.execute(payload);
    if (result.isRight())
      return result.getRight().get();

    int status = 500;
    final BaseError error = result.getLeft().get();

    if (error instanceof InvalidPayloadError)
      status = 400;

    return ResponseEntity
        .status(status)
        .body(error.toBody());
  }

  @PutMapping
  Object updateUser(@Valid @RequestBody CreateUser payload) {

    return "OK";
  }
}
