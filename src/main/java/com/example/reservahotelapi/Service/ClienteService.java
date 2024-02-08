package com.example.reservahotelapi.Service;


import com.example.reservahotelapi.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ClienteService {


    private final ClienteRepository clienteRepository;

    public int verificarDisponibilidade(LocalDate data) {
        return 10;
    }
}
