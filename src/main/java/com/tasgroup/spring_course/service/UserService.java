package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.controller.model.CreateUserResponse;
import com.tasgroup.spring_course.controller.model.GetUserResponse;
import com.tasgroup.spring_course.service.dto.CreateUserDto;
import com.tasgroup.spring_course.service.mapper.CreateUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    private final CreateUserMapper createUserMapper;

    public GetUserResponse getUser(String username) {
        GetUserResponse user = new GetUserResponse();
        user.setUsername(username);

        LocalDate currentDate = LocalDate.now();

        user.setDataCreazione(currentDate);
        user.setRole("Admin");
        user.setPaeseResidenza("Italy");

        return user;
    }

    public CreateUserResponse createUser(CreateUserDto createUserDto) {

        createUserDto.setDataCreazione(LocalDateTime.now());

        return createUserMapper.createUserResponseMapper(createUserDto);
    }
}
