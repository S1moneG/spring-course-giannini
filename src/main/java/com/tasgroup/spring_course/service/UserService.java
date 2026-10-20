package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.model.CreateUserResponse;
import com.tasgroup.spring_course.model.GetUserResponse;
import com.tasgroup.spring_course.service.dto.CreateUserDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    public GetUserResponse getUser(String username) {
        GetUserResponse user = new GetUserResponse();
        user.setUsername(username);

        LocalDate currentDate = LocalDate.now();

        user.setDataCreazione(currentDate);
        user.setRole("Admin");
        user.setPaeseResidenza("Italy");

        return user;
    }

    public CreateUserResponse createUser(CreateUserDto createUserRequest) {
        CreateUserResponse createUserResponse = new CreateUserResponse();

        createUserResponse.setDataCreazione(LocalDate.now());

        if(createUserRequest.getUsername() != null) {
            createUserResponse.setUsername(createUserRequest.getUsername());
        }
        if(createUserRequest.getRole() != null) {
            createUserResponse.setRole(createUserRequest.getRole());
        }
        if(createUserRequest.getResidenza() != null) {
            createUserResponse.setResidenza(createUserRequest.getResidenza());
        }

        return createUserResponse;
    }
}
