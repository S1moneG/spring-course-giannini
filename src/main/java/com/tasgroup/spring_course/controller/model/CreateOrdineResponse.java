package com.tasgroup.spring_course.controller.model;

import com.tasgroup.spring_course.utils.enumerations.OrdineStato;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateOrdineResponse {

    @Schema(
            description = "Id del ordine creato",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull
    private Long id;

    @Schema(
            description = "Codice del ordine creato",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String codiceOrdine;

    @Schema(
            description = "Data creazione ordine",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "string",
            format = "date-time"
    )
    @NotNull
    private LocalDateTime dataOrdine;

    @Schema(
            description = "Nome del cliente",
            example = "Luigi",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String nomeCliente;

    @Schema(
            description = "Cognome del cliente",
            example = "Verdi",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String cognomeCliente;

    @Schema(
            description = "Email del cliente",
            example = "simo.g@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "string",
            format = "email"
    )
    @NotBlank
    private String emailCliente;

    @Schema(
            description = "Telefono del cliente",
            example = "3455567833"
    )
    @NotNull
    private String telefonoCliente;

    @Schema(
            description = "Via dove spedire l'ordine",
            example = "Via Vittorio 25",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String indirizzoSpedizione;

    @Schema(
            description = "Citta dove spedire l'ordine",
            example = "Milano",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String cittaSpedizione;

    @Schema(
            description = "Cap della citta",
            example = "20019",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String cap;

    @Schema(
            description = "Nome del prodotto",
            example = "Acqua Sant'Anna",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String prodotto;

    @Schema(
            description = "Quantita del prodotto",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private int quantita;

    @Schema(
            description = "Prezzo del singolo prodotto",
            example = "20.00",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private double prezzoUnitario;

    @Schema(
            description = "Prezzo totale del ordine",
            example = "101.1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private double totale;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            implementation = OrdineStato.class,
            examples = "RICEVUTO"
    )
    @NotBlank
    private OrdineStato stato;

}
