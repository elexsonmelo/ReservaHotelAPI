package com.example.reservahotelapi.dto;

import com.example.reservahotelapi.model.Endereco;
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
    private Long quartoId;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
}
