package com.cleanarch.cleanarch.features.user.dtos;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cleanarch.cleanarch.features.user.entities.User;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthUser implements UserDetails {
  private UUID id;
  private String name;
  private String email;
  private String password;

  public static AuthUser of(User user) {
    return AuthUser.builder()
        .name(user.getName())
        .email(user.getEmail())
        .id(user.getId())
        .password(user.getPassword())
        .build();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
