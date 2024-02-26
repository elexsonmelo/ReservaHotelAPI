package com.example.reservahotelapi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table (name = "reserva")
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private double valorReserva;
    @Column
    private LocalDate dataEntrada;
    @Column
    private LocalDate dataSaida;
    @Column
    private int duracaoEmDias;
    @JoinColumn(name = "cliente_id")
    @ManyToOne
    private Cliente cliente;
    @JoinColumn(name = "quarto_id")
    @ManyToOne
    private Quarto quarto;
}




