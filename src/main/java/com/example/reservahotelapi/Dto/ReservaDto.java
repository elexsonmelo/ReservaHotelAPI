package com.example.reservahotelapi.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservaDto implements Serializable {
    private Long id;
    private ClienteDto cliente;
    private QuartoDto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
}
