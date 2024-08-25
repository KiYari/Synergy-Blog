package ru.dev.kiyari.auth.service.provider;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.dev.kiyari.auth.service.TokenService;

public interface JwtAuthenticationProvider extends TokenService, UserDetailsService {
}
