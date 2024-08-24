package ru.dev.kiyari.auth.service;

import ru.dev.kiyari.auth.model.dto.SignInDto;
import ru.dev.kiyari.auth.model.dto.SignUpDto;
import ru.dev.kiyari.auth.model.dto.TokenResponseDto;

public interface AuthenticationService {
    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    TokenResponseDto signUp(SignUpDto request);

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public TokenResponseDto signIn(SignInDto request);
}