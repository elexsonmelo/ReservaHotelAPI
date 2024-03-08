package com.example.reservahotelapi.Service;


import com.example.reservahotelapi.Repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class DataUtilService {

    private final ReservaRepository reservaRepository;


        public static void validarData(LocalDate dataEntrada, LocalDate dataSaida) throws Exception {
            LocalDate hoje = LocalDate.now();
            if (dataEntrada.isBefore(hoje.plusDays(1))) {
                throw new Exception("A reserva deve ser feita com pelo menos 1 dia de antecedência.");
            }
            if (dataEntrada.isAfter(hoje.plusDays(30))) {
                throw new Exception("A reserva não pode ser feita com mais de 30 dias de antecedência.");
            }
            if (dataSaida.isAfter(dataEntrada.plusDays(3))) {
                throw new Exception("A estadia não pode ser superior a 3 dias.");
            }
        }
    private long calcularDuracaoEstadia(LocalDate dataEntrada, LocalDate dataSaida) {
        return dataEntrada.until(dataSaida, java.time.temporal.ChronoUnit.DAYS);
    }
}





