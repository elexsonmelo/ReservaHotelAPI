package com.example.reservahotelapi.Dto;

import com.example.reservahotelapi.Model.Quarto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuartoDTO implements Serializable {

    private Long id;
    private int numero;
    private Boolean estaDisponivel;

    public QuartoDTO(Quarto entity) {
        this.id = entity.getQuartoId();
        this.numero = entity.getNumero();
        this.estaDisponivel = entity.getEstaDisponivel();
    }
}

