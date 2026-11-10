package com.tasgroup.spring_course.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private BigDecimal total;

    public OrderDto(Long id, BigDecimal total) {
        this.id = id;
        this.total = total;
    }
}
