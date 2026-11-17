package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.client.ExternalUserClient;
import com.tasgroup.spring_course.controller.model.ExternalUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalUserService {

    private final ExternalUserClient externalUserClient;

     public ExternalUserResponse findUserBydId(Long id) {
         return externalUserClient.findUser(id);
     }
}
