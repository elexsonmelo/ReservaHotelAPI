package com.example.reservahotelapi.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;



@Service
public class UtilService {

    public void fazerReserva(LocalDate dataInicioEstadia) throws Exception {
        LocalDate hoje = LocalDate.now();
        LocalDate limiteInicioReserva = hoje.plusDays(30);
        if (dataInicioEstadia.isBefore(hoje.plusDays(1))) {
            throw new Exception("A reserva deve ser feita com no mínimo 1 dia de antecedência.");
        } else if (dataInicioEstadia.isAfter(limiteInicioReserva)) {
            throw new Exception("A reserva não pode ser solicitada com mais de 30 dias de antecedência.");
        } else if (ChronoUnit.DAYS.between(hoje, dataInicioEstadia) > 3) {
            throw new Exception("A estadia não pode ser superior a 3 dias.");
        }
    }
}
