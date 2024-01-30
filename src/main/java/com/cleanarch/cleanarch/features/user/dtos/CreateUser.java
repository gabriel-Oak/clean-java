package com.cleanarch.cleanarch.features.user.dtos;

public record CreateUser(
    String name,
    String email,
    String password) {
}