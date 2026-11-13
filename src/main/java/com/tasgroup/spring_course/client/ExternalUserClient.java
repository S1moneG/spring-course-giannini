package com.tasgroup.spring_course.client;

import com.tasgroup.spring_course.controller.model.ExternalUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@AllArgsConstructor
public class ExternalUserClient {

    private final RestClient restClient;

    public ExternalUserResponse findUser(Long id) {
        return restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(ExternalUserResponse.class);
    }
}
