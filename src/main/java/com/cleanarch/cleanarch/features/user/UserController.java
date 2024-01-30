package com.cleanarch.cleanarch.features.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleanarch.cleanarch.features.user.dtos.CreateUser;
import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.features.user.usecases.createUser.ICreateUserUsecase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController()
@RequestMapping("/user")
public class UserController {
  final private ICreateUserUsecase createUserUsecase;

  @PostMapping
  ReturnUser createUser(@RequestBody CreateUser payload) throws Exception {
    final ReturnUser result = createUserUsecase.execute(payload);
    return result;
  }
}
