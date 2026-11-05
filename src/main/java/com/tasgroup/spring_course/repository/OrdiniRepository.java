package com.tasgroup.spring_course.repository;

import com.tasgroup.spring_course.repository.model.OrdineEntity;
import com.tasgroup.spring_course.utils.enumerations.OrdineStato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdiniRepository extends JpaRepository<OrdineEntity, Long> {

    Optional<OrdineEntity> findByCodiceOrdine(String codiceOrdine);

    List<OrdineEntity> findByStato(OrdineStato stato);
}
