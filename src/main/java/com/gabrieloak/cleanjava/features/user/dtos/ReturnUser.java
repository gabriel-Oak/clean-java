package com.gabrieloak.cleanjava.features.user.dtos;

import java.util.UUID;

public record ReturnUser(
        UUID id,
        String name,
        String email) {
}
