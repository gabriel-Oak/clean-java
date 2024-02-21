package com.gabrieloak.cleanjava.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gabrieloak.cleanjava.features.user.usecases.decodeUserToken.IDecodeUserTokenUsecase;
import com.gabrieloak.cleanjava.features.user.usecases.loadUser.ILoadUserUsecase;
import com.gabrieloak.cleanjava.utils.errors.BaseError;

import io.jbock.util.Either;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
  @Autowired
  IDecodeUserTokenUsecase decodeUserToken;

  @Autowired
  ILoadUserUsecase loadUserUsecase;

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    final String token = getToken(request);
    if (token != null) {
      final Either<BaseError, String> subjectResult = decodeUserToken.execute(token);
      if (subjectResult.isRight()) {
        final String userEmail = subjectResult.getRight().get();
        final UserDetails user = loadUserUsecase.loadUserByUsername(userEmail);
        final Authentication authentication = new UsernamePasswordAuthenticationToken(
            user,
            null,
            user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    final String authHeader = request.getHeader("Authorization");
    return authHeader != null
        ? authHeader.replace("Bearer ", "")
        : null;
  }
}
