package ru.dev.kiyari.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dev.kiyari.auth.model.Role;
import ru.dev.kiyari.auth.model.dto.SignInDto;
import ru.dev.kiyari.auth.model.dto.SignUpDto;
import ru.dev.kiyari.auth.model.dto.TokenResponseDto;
import ru.dev.kiyari.auth.model.entity.User;
import ru.dev.kiyari.auth.service.AuthenticationService;
import ru.dev.kiyari.auth.service.JwtService;
import ru.dev.kiyari.auth.service.UserService;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    @Override
    public TokenResponseDto signUp(SignUpDto request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new TokenResponseDto(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    @Override
    public TokenResponseDto signIn(SignInDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new TokenResponseDto(jwt);
    }
}
