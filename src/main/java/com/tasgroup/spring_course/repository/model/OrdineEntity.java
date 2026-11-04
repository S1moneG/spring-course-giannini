package com.tasgroup.spring_course.repository.model;

import com.tasgroup.spring_course.utils.enumerations.OrdineStato;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordini")
@Data
@NoArgsConstructor
public class OrdineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    @Size(min = 20, max = 30)
    private String codiceOrdine;

    @Column(nullable = false, length = 50)
    @Size(min = 2, max = 50)
    private String nomeCliente;

    @Column(nullable = false, length = 50)
    @Size(min = 2, max = 50)
    private String cognomeCliente;

    @Column(nullable = false)
    @Email
    private String emailCliente;

    @Column(length = 20)
    @Size(max = 20)
    private String telefonoCliente;

    @Column(nullable = false, length = 120)
    @Size(min = 5, max = 120)
    private String indirizzoSpedizione;

    @Column(nullable = false, length = 50)
    @Size(min = 2, max = 50)
    private String cittaSpedizione;

    @Column(nullable = false, length = 5)
    @Size(min = 5, max = 5)
    private String cap;

    @Column(nullable = false, length = 80)
    @Size(min = 2, max = 80)
    private String prodotto;

    @Column(nullable = false)
    @Min(value = 1)
    @Max(value = 999)
    private int quantita;

    @Column(nullable = false)
    @Positive
    private double prezzoUnitario;

    @Column(nullable = false)
    @Positive
    private double totale;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrdineStato stato;

    @Column(nullable = false)
    private LocalDateTime dataOrdine;
}
