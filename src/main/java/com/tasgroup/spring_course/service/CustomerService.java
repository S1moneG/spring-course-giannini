package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.controller.model.CustomerResponse;
import com.tasgroup.spring_course.exception.MyAppException;
import com.tasgroup.spring_course.repository.CustomerRepository;
import com.tasgroup.spring_course.repository.model.CustomerEntity;
import com.tasgroup.spring_course.service.dto.CustomerDto;
import com.tasgroup.spring_course.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {

        log.info("inizio query findCustomerById, valore id = {}", id);
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(()-> new MyAppException("Customer: '" +id  + "' not found", HttpStatus.NOT_FOUND));
        CustomerDto customerDto = customerMapper.toDto(customerEntity);

        return customerMapper.toResponse(customerDto);
    }
}
