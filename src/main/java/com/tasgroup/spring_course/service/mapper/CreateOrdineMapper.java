package com.tasgroup.spring_course.service.mapper;

import com.tasgroup.spring_course.controller.model.CreateOrdineRequest;
import com.tasgroup.spring_course.controller.model.CreateOrdineResponse;
import com.tasgroup.spring_course.repository.model.OrdineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CreateOrdineMapper {

    @Mapping(
            target = "totale",
            expression = "java(mapTotaleOrdine(createOrdineRequest.getQuantita(), createOrdineRequest.getPrezzoUnitario()))"
    )
    public abstract OrdineEntity createOrdineRequestMapper(CreateOrdineRequest createOrdineRequest);

    public double mapTotaleOrdine(int quantita, double prezzoUnitario) {
        return quantita * prezzoUnitario;
    }

    public abstract CreateOrdineResponse createOrdineResponseMapper(OrdineEntity savedOrdineEntity);

}
