package com.example.reservahotelapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CepResultDto {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
