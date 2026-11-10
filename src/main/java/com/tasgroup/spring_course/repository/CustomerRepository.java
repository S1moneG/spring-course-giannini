package com.tasgroup.spring_course.repository;

import com.tasgroup.spring_course.repository.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
