package com.example.reservahotelapi.Service;


import com.example.reservahotelapi.Model.Reserva;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class DataUtilService {

    public void validarData(Reserva reserva) throws Exception {
        LocalDate hoje = LocalDate.now();
        validarAntecedencia(reserva.getDataEntrada(), hoje);
        validarDuracao(reserva.getDataEntrada(), reserva.getDataSaida());
        validarAntecedenciaMaxima(reserva.getDataEntrada());
    }
    private void validarAntecedencia(LocalDate dataEntrada, LocalDate hoje) throws Exception {
        if (dataEntrada.isBefore(hoje.plusDays(1))) {
            throw new Exception("A reserva deve ser feita com no mínimo 1 dia de antecedência");
        }
    }

    private void validarDuracao(LocalDate dataEntrada, LocalDate dataSaida) throws Exception {
        if (dataSaida.isBefore(dataEntrada) || dataSaida.isEqual(dataEntrada)) {
            throw new Exception("A data de saída deve ser posterior à data de entrada");
        }

        long duracaoEmDias = calcularDuracaoEmDias(dataEntrada, dataSaida);
        if (duracaoEmDias > 3) {
            throw new Exception("A estadia não pode ser superior a 3 dias");
        }
    }

    private void validarAntecedenciaMaxima(LocalDate dataEntrada) throws Exception {
        LocalDate limiteAntecedencia = LocalDate.now().plusDays(30);
        if (dataEntrada.isAfter(limiteAntecedencia)) {
            throw new Exception("A reserva não pode ser solicitada com mais de 30 dias de antecedência");
        }
    }

    private long calcularDuracaoEmDias(LocalDate dataEntrada, LocalDate dataSaida) {
        return dataEntrada.until(dataSaida, java.time.temporal.ChronoUnit.DAYS);
    }
}







