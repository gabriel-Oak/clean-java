package com.gabrieloak.cleanjava.features.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateUser(
        @NotBlank(message = "Nome não informado") String name,
        @NotBlank(message = "Email não informado") String email,
        @NotBlank(message = "Senha não informada") String password) {
}