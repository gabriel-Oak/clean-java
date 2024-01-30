package com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource;

import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.features.user.entities.User;
import com.cleanarch.cleanarch.features.user.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserInternalDatasource implements IUserInternalDatasource {
  final private UserRepository userRepository;

  @Override
  public ReturnUser saveUser(User newUser) {
    final User savedUser = userRepository.save(newUser);

    return new ReturnUser(
      savedUser.getId(), 
      savedUser.getName(), 
      savedUser.getEmail());
  }

}
