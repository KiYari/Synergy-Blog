package ru.dev.kiyari.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService extends TokenService{
    /**
     * Генерация токена
     *
     * @param userDetails данные пользователя
     * @return токен
     */
    String generateToken(UserDetails userDetails);
}
