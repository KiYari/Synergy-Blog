package ru.dev.kiyari.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dev.kiyari.auth.model.dto.SignInDto;
import ru.dev.kiyari.auth.model.dto.SignUpDto;
import ru.dev.kiyari.auth.model.dto.TokenResponseDto;
import ru.dev.kiyari.auth.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public TokenResponseDto signUp(@RequestBody @Valid SignUpDto request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public TokenResponseDto signIn(@RequestBody @Valid SignInDto request) {
        return authenticationService.signIn(request);
    }
}
