package com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource;

import com.cleanarch.cleanarch.features.user.dtos.ReturnUser;
import com.cleanarch.cleanarch.features.user.entities.User;

public interface IUserInternalDatasource {
  ReturnUser saveUser(User payload);
}
