package com.tasgroup.spring_course.utils.enumerations;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        enumAsRef = true,
        description = "Stato del ordine, valori possibili: RICEVUTO, IN_LAVORAZIONE, SPEDITO, ANNULLATO",
        example = "RICEVUTO"
)
public enum OrdineStato {
    RICEVUTO,
    IN_LAVORAZIONE,
    SPEDITO,
    ANNULLATO
}
