package com.cleanarch.cleanarch.features.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleanarch.cleanarch.errors.BaseError;
import com.cleanarch.cleanarch.errors.InvalidPayloadError;
import com.cleanarch.cleanarch.features.user.dtos.CreateUser;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.features.user.usecases.createUser.ICreateUserUsecase;

import io.jbock.util.Either;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController()
@RequestMapping("/user")
public class UserController {
  final private ICreateUserUsecase createUserUsecase;

  @PostMapping
  Object createUser(@RequestBody CreateUser payload) {
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
}
