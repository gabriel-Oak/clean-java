package com.cleanarch.cleanarch.features.user.usecases.loadUser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.cleanarch.cleanarch.features.user.dtos.AuthUser;
import com.cleanarch.cleanarch.features.user.entities.User;
import com.cleanarch.cleanarch.utils.errors.DatasourceError;

import io.jbock.util.Either;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoadUserUsecase implements ILoadUserUsecase {
  private final IUserInternalDatasource userDatasource;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Either<DatasourceError, User> userResult = userDatasource.loadUserByEmail(username);
    if (userResult.isLeft())
      throw new UsernameNotFoundException("Usuário não encontrado");

    return AuthUser.of(userResult.getRight().get());
  }

}
