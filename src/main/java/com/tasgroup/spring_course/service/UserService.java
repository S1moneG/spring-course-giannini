package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.model.CreateUserRequest;
import com.tasgroup.spring_course.model.CreateUserResponse;
import com.tasgroup.spring_course.model.GetUserResponse;
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

    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        CreateUserResponse createdUser = new CreateUserResponse();

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
