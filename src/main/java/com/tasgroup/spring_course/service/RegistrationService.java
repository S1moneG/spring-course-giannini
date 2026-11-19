package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.controller.model.RegisterRequest;
import com.tasgroup.spring_course.controller.model.RegisterResponse;
import com.tasgroup.spring_course.repository.UserAccountRepository;
import com.tasgroup.spring_course.repository.model.UserAccountEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistrationService {

    private final UserAccountRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(
            UserAccountRepository userRepository,
            PasswordEncoder passwordEncoder) {


        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Username già utilizzato"
            );
        }

        UserAccountEntity user = new UserAccountEntity(
                request.username(),
                passwordEncoder.encode(request.password()),
                "USER"
        );

        UserAccountEntity savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getUsername(),
                savedUser.getRole()
        );
    }

}
