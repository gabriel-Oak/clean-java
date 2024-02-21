package com.gabrieloak.cleanjava.features.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gabrieloak.cleanjava.features.user.datasources.userInternalDatasource.IUserInternalDatasource;
import com.gabrieloak.cleanjava.features.user.datasources.userInternalDatasource.UserInternalDatasource;
import com.gabrieloak.cleanjava.features.user.repositories.UserRepository;
import com.gabrieloak.cleanjava.features.user.usecases.authUser.AuthUserUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.authUser.IAuthUserUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.createUser.CreateUserUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.createUser.ICreateUserUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.decodeUserToken.DecodeUserTokenUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.decodeUserToken.IDecodeUserTokenUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.generateUserToken.GenerateUserTokenUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.generateUserToken.IGenerateUserTokenUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.loadUser.ILoadUserUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.loadUser.LoadUserUsecase;

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
  IAuthUserUsecase authUserUsecase(AuthenticationManager authenticationManager) {
    return new AuthUserUsecase(authenticationManager);
  }

  @Bean
  ILoadUserUsecase loadUserUsecase(IUserInternalDatasource datasource) {
    return new LoadUserUsecase(datasource);
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
