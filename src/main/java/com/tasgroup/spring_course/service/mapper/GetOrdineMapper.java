package com.tasgroup.spring_course.service.mapper;

import com.tasgroup.spring_course.controller.model.GetOrdineResponse;
import com.tasgroup.spring_course.repository.model.OrdineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class GetOrdineMapper {

    public abstract GetOrdineResponse getOrdineResponseMapper(OrdineEntity ordineEntity);

}
