package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Reserva;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class DataUtilService {

    private final ReservaService reservaService;


    private void validarData(Reserva reserva) throws Exception {
        validarDataEntrada(reserva);
        validarDataLimite(reserva);

    }
    public void validarDataEntrada(Reserva reserva) throws Exception {
        LocalDate hoje = LocalDate.now();
        if (reserva.getDataEntrada().isBefore(hoje.plusDays(1))) {
            throw new Exception("A reserva deve ser feita com no mínimo 1 dia de antecedência");
        }
    }
    public void validarDataLimite(Reserva reserva) throws Exception {
        LocalDate hoje = LocalDate.now();
        if (reserva.getDataEntrada().isAfter(hoje.plusDays(30))) {
            throw new Exception("A reserva não pode ser solicitada com mais de 30 dias de antecedência");
        }
    }
}


