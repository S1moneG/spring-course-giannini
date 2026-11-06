package com.tasgroup.spring_course.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerResponse {
    private Long id;

    private String name;

    private List<OrderResponse> orders;
}
