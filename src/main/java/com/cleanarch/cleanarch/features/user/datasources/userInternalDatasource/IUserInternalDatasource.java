package com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource;

import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.features.user.entities.User;
import com.cleanarch.cleanarch.utils.errors.DatasourceError;

import io.jbock.util.Either;

public interface IUserInternalDatasource {
  ReturnUser saveUser(User payload);

  Either<DatasourceError, ReturnUser> findUserByEmail(String email);

  Either<DatasourceError, User> loadUserByEmail(String email);
}
