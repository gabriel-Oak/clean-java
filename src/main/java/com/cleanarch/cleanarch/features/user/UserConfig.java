package com.cleanarch.cleanarch.features.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.UserInternalDatasource;
import com.cleanarch.cleanarch.features.user.repositories.UserRepository;
import com.cleanarch.cleanarch.features.user.usecases.createUser.CreateUserUsecase;
import com.cleanarch.cleanarch.features.user.usecases.createUser.ICreateUserUsecase;
import com.cleanarch.cleanarch.features.user.usecases.loadUser.LoadUserUsecase;

@Configuration
public class UserConfig {
  @Bean
  PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  IUserInternalDatasource userInternalDatasource(UserRepository repository) {
    return new UserInternalDatasource(repository);
  }

  @Bean
  ICreateUserUsecase createUserUsecase(
      IUserInternalDatasource datasource,
      PasswordEncoder encoder) {
    return new CreateUserUsecase(datasource, encoder);
  }

  @Bean
  UserDetailsService userDetailsService(IUserInternalDatasource datasource) {
    return new LoadUserUsecase(datasource);
  }

  @Bean
  UserController userController(ICreateUserUsecase createUserUsecase) {
    return new UserController(createUserUsecase);
  }
}
