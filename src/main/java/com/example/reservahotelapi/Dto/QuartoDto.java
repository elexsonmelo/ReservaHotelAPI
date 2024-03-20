package com.example.reservahotelapi.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuartoDto implements Serializable {
    private Long id;
    private int numero;
    private Boolean estaDisponivel;
}

