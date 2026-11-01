package com.tasgroup.spring_course.controller;

import com.tasgroup.spring_course.controller.model.CreateUserRequest;
import com.tasgroup.spring_course.controller.model.CreateUserResponse;
import com.tasgroup.spring_course.controller.model.GetUserResponse;
import com.tasgroup.spring_course.service.dto.CreateUserDto;
import com.tasgroup.spring_course.service.mapper.CreateUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tasgroup.spring_course.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserMapper createUserMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable String username) {
        GetUserResponse userResponse = userService.getUser(username);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        CreateUserDto createUserDto = createUserMapper.createUserDtoMapperFromRequest(createUserRequest);
        CreateUserResponse createUserResponse = userService.createUser(createUserDto);

        return new ResponseEntity<>(createUserResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }
}
