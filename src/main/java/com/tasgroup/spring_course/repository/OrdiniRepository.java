package com.tasgroup.spring_course.repository;

import com.tasgroup.spring_course.repository.model.OrdineEntity;
import com.tasgroup.spring_course.utils.enumerations.OrdineStato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdiniRepository extends JpaRepository<OrdineEntity, Long> {

    Optional<OrdineEntity> findByCodiceOrdine(String codiceOrdine);

    List<OrdineEntity> findByStato(OrdineStato stato);

    @Query(value =
            """
                select
                    o.stato as stato,
                    COUNT(o) as numeroOrdini
                from OrdineEntity o
                group by o.stato
           """
    )
    List<OrdineEntity.OrdineStatoCountProjection> countOrdiniByStato();
}
