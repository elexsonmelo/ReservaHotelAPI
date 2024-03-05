package com.example.reservahotelapi.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class DataUtilService {


        public static void validarData(LocalDate dataInicio, LocalDate dataFim) throws Exception {
            LocalDate hoje = LocalDate.now();
            if (dataInicio.isBefore(hoje.plusDays(1))) {
                throw new Exception("A reserva deve ser feita com pelo menos 1 dia de antecedência.");
            }
            if (dataInicio.isAfter(hoje.plusDays(30))) {
                throw new Exception("A reserva não pode ser feita com mais de 30 dias de antecedência.");
            }
            if (dataFim.isAfter(dataInicio.plusDays(3))) {
                throw new Exception("A estadia não pode ser superior a 3 dias.");
            }
        }
    private long calcularDuracaoEstadia(LocalDate dataEntrada, LocalDate dataSaida) {
        return dataEntrada.until(dataSaida, java.time.temporal.ChronoUnit.DAYS);
    }
}





