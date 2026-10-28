package com.tasgroup.spring_course.service.mapper;

import com.tasgroup.spring_course.controller.model.GetUserResponse;
import com.tasgroup.spring_course.repository.model.UserEntity;
import com.tasgroup.spring_course.service.dto.GetUserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class GetUserMapper {
    public abstract GetUserRequestDto getUserDtoMapper(UserEntity userEntity);

    public abstract GetUserResponse getUserResponseMapper(GetUserRequestDto getUserRequestDto);
}
