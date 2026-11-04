package com.tasgroup.spring_course.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class CreateOrdineRequest {

    @Schema(
            description = "Nome del cliente",
            example = "Luigi",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 2,
            maxLength = 50
    )
    @NotBlank(message = "il campo nomeCliente è mandatorio")
    @Size(min = 2, max = 50, message = "nomeCliente deve avere lunghezza compresa tra 2-50")
    private String nomeCliente;

    @Schema(
            description = "Cognome del cliente",
            example = "Verdi",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 2,
            maxLength = 50
    )
    @NotBlank(message = "il campo cognomeCliente è mandatorio")
    @Size(min = 2, max = 50, message = "cognomeCliente deve avere lunghezza compresa tra 2-50")
    private String cognomeCliente;

    @Schema(
            description = "Email del cliente",
            example = "simo.g@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 100
    )
    @NotBlank(message = "il campo emailCliente è mandatorio")
    @Email(message = "email inserita non è valida")
    @Size(max = 100, message = "emailCliente può avere lunghezza massima 100")
    private String emailCliente;

    @Schema(
            description = "Telefono del cliente",
            example = "3455567833",
            maxLength = 20
    )
    @Size(max = 20, message = "telefonoCliente può avere lunghezza massima 20")
    private String telefonoCliente;

    @Schema(
            description = "Via dove spedire l'ordine",
            example = "Via Vittorio 25",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 5,
            maxLength = 120
    )
    @NotBlank(message = "il campo indirizzoSpedizione è mandatorio")
    @Size(min = 5, max = 120, message = "indirizzoSpedizione deve avere lunghezza compresa tra 5-120")
    private String indirizzoSpedizione;

    @Schema(
            description = "Citta dove spedire l'ordine",
            example = "Milano",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 2,
            maxLength = 50
    )
    @NotBlank(message = "il campo cittaSpedizione è mandatorio")
    @Size(min = 2, max = 50, message = "cittaSpedizione deve avere lunghezza compresa tra 2-50")
    private String cittaSpedizione;

    @Schema(
            description = "Cap della citta",
            example = "20019",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "il campo cap è mandatorio")
    @Pattern(regexp = "^[0-9]{5}$", message = "cap inserito non è valido")
    private String cap;

    @Schema(
            description = "Nome del prodotto",
            example = "Acqua Sant'Anna",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 2,
            maxLength = 80
    )
    @NotBlank(message = "il campo prodotto è mandatorio")
    @Size(min = 2, max = 80, message = "prodotto deve avere lunghezza compresa tra 2-80")
    private String prodotto;

    @Schema(
            description = "Citta dove spedire l'ordine",
            example = "Milano",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "1",
            maximum = "999"
    )
    @NotNull(message = "il campo quantita è mandatorio")
    @Min(value = 1, message = "quantita non può essere inferiore di 1")
    @Max(value = 999, message = "quantita non può essere superiore di 999")
    private int quantita;

    @Schema(
            description = "Citta dove spedire l'ordine",
            example = "Milano",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "0.01"
    )
    @NotNull(message = "il campo prezzoUnitario è mandatorio")
    @Positive(message = "prezzoUnitario deve avere un valore positivo")
    private double prezzoUnitario;

}
