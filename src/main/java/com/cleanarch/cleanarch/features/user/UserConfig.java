package com.cleanarch.cleanarch.features.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.cleanarch.cleanarch.features.user.datasources.userInternalDatasource.UserInternalDatasource;
import com.cleanarch.cleanarch.features.user.repositories.UserRepository;
import com.cleanarch.cleanarch.features.user.usecases.authUser.AuthUserUsecase;
import com.cleanarch.cleanarch.features.user.usecases.authUser.IAuthUserUsecase;
import com.cleanarch.cleanarch.features.user.usecases.createUser.CreateUserUsecase;
import com.cleanarch.cleanarch.features.user.usecases.createUser.ICreateUserUsecase;
import com.cleanarch.cleanarch.features.user.usecases.decodeUserToken.DecodeUserTokenUsecase;
import com.cleanarch.cleanarch.features.user.usecases.decodeUserToken.IDecodeUserTokenUsecase;
import com.cleanarch.cleanarch.features.user.usecases.generateUserToken.GenerateUserTokenUsecase;
import com.cleanarch.cleanarch.features.user.usecases.generateUserToken.IGenerateUserTokenUsecase;
import com.cleanarch.cleanarch.features.user.usecases.loadUser.LoadUserUsecase;

@Configuration
public class UserConfig {

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
  IGenerateUserTokenUsecase generateUserTokenUsecase() {
    return new GenerateUserTokenUsecase();
  }

  @Bean
  IDecodeUserTokenUsecase decodeUserTokenUsecase() {
    return new DecodeUserTokenUsecase();
  }

  @Bean
  IAuthUserUsecase authUserUsecase(
      AuthenticationManager authenticationManager,
      IUserInternalDatasource datasource) {
    return new AuthUserUsecase(authenticationManager, datasource);
  }

  @Bean
  UserDetailsService userDetailsService(IUserInternalDatasource datasource) {
    return new LoadUserUsecase(datasource);
  }

  @Bean
  UserController userController(
      ICreateUserUsecase createUserUsecase,
      IAuthUserUsecase authUserUsecase,
      IGenerateUserTokenUsecase generateUserTokenUsecase) {
    return new UserController(
        createUserUsecase,
        authUserUsecase,
        generateUserTokenUsecase);
  }
}
