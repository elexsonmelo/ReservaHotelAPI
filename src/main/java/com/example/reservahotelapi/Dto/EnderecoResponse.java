package com.example.reservahotelapi.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class EnderecoResponse {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
