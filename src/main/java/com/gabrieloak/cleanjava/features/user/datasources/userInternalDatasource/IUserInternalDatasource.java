package com.gabrieloak.cleanjava.features.user.datasources.userInternalDatasource;

import com.gabrieloak.cleanjava.features.user.dtos.ReturnUser;
import com.gabrieloak.cleanjava.features.user.entities.User;
import com.gabrieloak.cleanjava.utils.errors.DatasourceError;

import io.jbock.util.Either;

public interface IUserInternalDatasource {
  ReturnUser saveUser(User payload);

  Either<DatasourceError, ReturnUser> findUserByEmail(String email);

  Either<DatasourceError, User> loadUserByEmail(String email);
}
