package com.cleanarch.cleanarch.features.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.UserInternalDatasource;
import com.cleanarch.cleanarch.features.user.repositories.UserRepository;
import com.cleanarch.cleanarch.features.user.usecases.createUser.CreateUserUsecase;
import com.cleanarch.cleanarch.features.user.usecases.createUser.ICreateUserUsecase;

@Configuration
public class UserConfig {
  @Bean
  IUserInternalDatasource userInternalDatasource(UserRepository repository) {
    return new UserInternalDatasource(repository);
  }

  @Bean
  ICreateUserUsecase createUserUsecase(IUserInternalDatasource datasource) {
    return new CreateUserUsecase(datasource);
  }
  
  @Bean
  UserController userController(ICreateUserUsecase createUserUsecase) {
    return new UserController(createUserUsecase);
  }
}
