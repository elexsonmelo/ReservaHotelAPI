package com.example.reservahotelapi.Dto;

import com.example.reservahotelapi.Model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDto implements Serializable {
    private Long id;
    private String nome;
    private String email;
    private String cep;
    private Endereco endereco;
}