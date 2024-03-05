package com.example.reservahotelapi.Service;


import com.example.reservahotelapi.Dto.CepResultDto;
import com.example.reservahotelapi.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public CepResultDto consultarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CepResultDto> resp = restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json/", cep), CepResultDto.class);
        return resp.getBody();
    }
}
