package com.tasgroup.spring_course.service.mapper;

import com.tasgroup.spring_course.model.CreateUserRequest;
import com.tasgroup.spring_course.service.dto.CreateUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CreateUserMapper {

    @Mapping(
            target = "residenza",
            expression = "java(mapResidenza(createUserRequest.getPaeseResidenza(), createUserRequest.getCittaResidenza()))"
    )
    public abstract CreateUserDto createUserDtoMapper(CreateUserRequest createUserRequest);

    public String mapResidenza(String paese, String citta) {
        return paese + " - " + citta;
    }

}
