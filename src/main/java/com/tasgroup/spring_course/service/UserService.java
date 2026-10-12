package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.dto.CreateUserRequestDto;
import com.tasgroup.spring_course.dto.CreateUserResponseDto;
import com.tasgroup.spring_course.dto.GetUserResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {


    public GetUserResponseDto getUser(String username) {
        GetUserResponseDto user = new GetUserResponseDto();
        user.setUsername(username);

        LocalDate currentDate = LocalDate.now();

        user.setDataCreazione(currentDate);
        user.setRole("Admin");
        user.setPaeseResidenza("Italy");

        return user;
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequest) {
        CreateUserResponseDto createdUser = new CreateUserResponseDto();

        if(createUserRequest.getUsername() != null) {
            createdUser.setUsername(createUserRequest.getUsername());
        }
        createdUser.setDataCreazione(LocalDate.now());
        if(createUserRequest.getRole() != null) {
            createdUser.setRole(createUserRequest.getRole());
        }
        if(createUserRequest.getPaeseResidenza() != null) {
            createdUser.setPaeseResidenza(createUserRequest.getPaeseResidenza());
        }

        return createdUser;
    }
}
