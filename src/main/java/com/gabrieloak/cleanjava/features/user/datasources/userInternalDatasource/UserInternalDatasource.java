package com.gabrieloak.cleanjava.features.user.datasources.userInternalDatasource;

import java.util.Optional;

import com.gabrieloak.cleanjava.features.user.datasources.userInternalDatasource.errors.UserInternalDatasourceNotFoundError;
import com.gabrieloak.cleanjava.features.user.dtos.ReturnUser;
import com.gabrieloak.cleanjava.features.user.entities.User;
import com.gabrieloak.cleanjava.features.user.repositories.UserRepository;
import com.gabrieloak.cleanjava.utils.errors.DatasourceError;

import io.jbock.util.Either;
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

  @Override
  public Either<DatasourceError, ReturnUser> findUserByEmail(String email) {
    try {
      final Optional<User> user = userRepository.findUserByEmail(email);
      return user.isPresent()
          ? Either.right(new ReturnUser(
              user.get().getId(),
              user.get().getName(),
              user.get().getEmail()))
          : Either.left(new UserInternalDatasourceNotFoundError());
    } catch (Exception e) {
      return Either.left(new DatasourceError(e.getMessage()));
    }
  }

  @Override
  public Either<DatasourceError, User> loadUserByEmail(String email) {
    try {
      final Optional<User> user = userRepository.findUserByEmail(email);
      return user.isPresent()
          ? Either.right(user.get())
          : Either.left(new UserInternalDatasourceNotFoundError());
    } catch (Exception e) {
      return Either.left(new DatasourceError(e.getMessage()));
    }
  }
}
