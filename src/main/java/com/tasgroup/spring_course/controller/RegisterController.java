package com.tasgroup.spring_course.controller;

import com.tasgroup.spring_course.controller.model.LoginRequest;
import com.tasgroup.spring_course.controller.model.RegisterRequest;
import com.tasgroup.spring_course.controller.model.RegisterResponse;
import com.tasgroup.spring_course.controller.model.TokenResponse;
import com.tasgroup.spring_course.service.RegistrationService;
import com.tasgroup.spring_course.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public RegisterController(
            RegistrationService registrationService,
            AuthenticationManager authenticationManager,
            TokenService tokenService
    ) {
        this.registrationService = registrationService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = registrationService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody LoginRequest request) {
        try {
            var authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            return ResponseEntity
                    .ok()
                    .header("Cache-Control", "no-store")
                    .body(tokenService.issue(authentication));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenziali non valide");
        }
    }
}
