package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Dto.CepResultDto;
import com.example.reservahotelapi.Service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

private final ClienteService clienteService;

    @GetMapping("{cep}")
    public CepResultDto consultaCep(@PathVariable("cep") String cep) {
        return clienteService.consultarCep(cep);
    }
}

