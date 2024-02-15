package com.cleanarch.cleanarch.features.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthPayload(
    @NotBlank(message = "Email não informado") String email,
    @NotBlank(message = "Senha não informada") String password) {
}
