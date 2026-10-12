package com.tasgroup.spring_course.controller;

import com.tasgroup.spring_course.dto.CreateUserRequestDto;
import com.tasgroup.spring_course.dto.CreateUserResponseDto;
import com.tasgroup.spring_course.dto.GetUserResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tasgroup.spring_course.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<GetUserResponseDto> getUser(@PathVariable String username) {
        GetUserResponseDto userResponse = userService.getUser(username);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequest) {
        CreateUserResponseDto createUserResponseDto = userService.createUser(createUserRequest);
        return new ResponseEntity<>(createUserResponseDto, HttpStatus.OK);
    }
}
