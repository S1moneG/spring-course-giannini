package com.tasgroup.spring_course.controller;

import com.tasgroup.spring_course.controller.model.ExternalUserResponse;
import com.tasgroup.spring_course.service.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external-users")
public class ExternalUserController {

    @Autowired
    private ExternalUserService externalUserService;

    @GetMapping("/{id}")
    public ExternalUserResponse findUser(@PathVariable Long id) {
        return externalUserService.findUserBydId(id);
    }
}
