package com.example.reservahotelapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table (name = "reserva")
public class Reserva implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private double valorReserva;
    @Column
    private LocalDate dataEntrada;
    @Column
    private LocalDate dataSaida;
    @Column(nullable = false)
    private int duracaoEmDias;
    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    @JoinColumn
    @ManyToOne(cascade = CascadeType.MERGE)
    private Quarto quarto;
}




