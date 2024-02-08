package com.example.reservahotelapi.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Getter
public class EnderecoRequest {

    private String cep;
}
