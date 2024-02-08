package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

private final ClienteService clienteService;
    @GetMapping("/disponibilidade")
    public int consultarDisponibilidade(@RequestParam String data) {
        LocalDate dataConsulta = LocalDate.parse(data);
        int quartosDisponiveis = clienteService.verificarDisponibilidade(dataConsulta);
        return quartosDisponiveis;
    }
}
