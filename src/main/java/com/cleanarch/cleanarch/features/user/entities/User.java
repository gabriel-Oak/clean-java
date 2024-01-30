package com.cleanarch.cleanarch.features.user.entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter()
@Setter()
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;
  @Column(unique=true)
  private String email;
  private String password;
}
