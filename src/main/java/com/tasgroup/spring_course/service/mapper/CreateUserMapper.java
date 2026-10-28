package com.tasgroup.spring_course.service.mapper;

import com.tasgroup.spring_course.controller.model.CreateUserRequest;
import com.tasgroup.spring_course.controller.model.CreateUserResponse;
import com.tasgroup.spring_course.repository.model.UserEntity;
import com.tasgroup.spring_course.service.dto.CreateUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CreateUserMapper {

    @Mapping(
            target = "residenza",
            expression = "java(mapResidenza(createUserRequest.getPaeseResidenza(), createUserRequest.getCittaResidenza()))"
    )
    public abstract CreateUserDto createUserDtoMapperFromRequest(CreateUserRequest createUserRequest);

    public String mapResidenza(String paese, String citta) {
        return paese + " - " + citta;
    }

    public abstract CreateUserResponse createUserResponseMapper(CreateUserDto createUserDto);

    @Mapping(
            target = "dataCreazione",
            expression = "java(mapDataCreazione())"
    )
    public abstract UserEntity createUserEntityMapper(CreateUserDto createUserDto);

    public LocalDateTime mapDataCreazione() {
        return LocalDateTime.now();
    }

}
