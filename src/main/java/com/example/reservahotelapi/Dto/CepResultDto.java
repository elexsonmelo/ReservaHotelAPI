package com.example.reservahotelapi.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepResultDto {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
