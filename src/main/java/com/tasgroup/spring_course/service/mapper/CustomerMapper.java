package com.tasgroup.spring_course.service.mapper;

import com.tasgroup.spring_course.controller.model.CustomerResponse;
import com.tasgroup.spring_course.controller.model.OrderResponse;
import com.tasgroup.spring_course.repository.model.CustomerEntity;
import com.tasgroup.spring_course.service.dto.CustomerDto;
import com.tasgroup.spring_course.service.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

    public CustomerResponse toResponse(CustomerDto customerDto) {
        List<OrderResponse> orders = customerDto.getOrders()
                .stream()
                .map(orderDto -> new OrderResponse(
                        orderDto.getId(), orderDto.getTotal()
                ))
                .toList();

        return new CustomerResponse(
                customerDto.getId(), customerDto.getName(), orders
        );
    }

    public CustomerDto toDto(CustomerEntity customerEntity) {
        List<OrderDto> orders = customerEntity.getOrders()
                .stream()
                .map(orderDto -> new OrderDto(
                        orderDto.getId(), orderDto.getTotal()
                ))
                .toList();

        return new CustomerDto(
                customerEntity.getId(), customerEntity.getName(), orders
        );
    }
}
