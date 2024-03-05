package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Reserva;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class DataUtilService {


    private void validarData(Reserva reserva) throws Exception {
        validarDataEntrada(reserva.getDataEntrada());
        validarAntecedenciaMaxima(reserva.getDataEntrada());
        validarDuracao(reserva.getDuracaoEmDias());
    }
    public void validarDataEntrada(LocalDate dataEntrada) throws Exception {
        LocalDate hoje = LocalDate.now();
        if (dataEntrada.isBefore(hoje.plusDays(1))) {
            throw new Exception("A reserva deve ser feita com no mínimo 1 dia de antecedência");
        }
    }
    private void validarAntecedenciaMaxima(LocalDate dataEntrada) throws Exception {
        LocalDate hoje = LocalDate.now();
        if (dataEntrada.isAfter(hoje.plusDays(30))) {
            throw new Exception("A reserva não pode ser solicitada com mais de 30 dias de antecedência");
        }
    }
    public void validarDuracao(int duracaoEmDias) throws Exception {
        if (duracaoEmDias > 3) {
            throw new Exception("A estadia não pode ser superior a 3 dias");
        }
    }
    private long calcularDuracaoEmDias(LocalDate dataEntrada, LocalDate dataSaida) {
        return dataEntrada.until(dataSaida, java.time.temporal.ChronoUnit.DAYS);
    }
}


