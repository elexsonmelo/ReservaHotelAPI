package com.example.reservahotelapi.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class DataUtilService {

    public void validarData(LocalDate dataEntrada, LocalDate dataSaida) throws Exception {
        LocalDate hoje = LocalDate.now();
        validarAntecedencia(dataEntrada, hoje);
        validarDuracaoEstadia(dataEntrada, dataSaida);
        validarAntecedenciaMaxima(dataEntrada);
    }
    private void validarAntecedencia(LocalDate dataEntrada, LocalDate hoje) throws Exception {
        if (dataEntrada.isBefore(hoje.plusDays(1))) {
            throw new Exception("A reserva deve ser feita com no mínimo 1 dia de antecedência");
        }
    }

    private void validarDuracaoEstadia(LocalDate dataEntrada, LocalDate dataSaida) throws Exception {
        if (dataEntrada.plusDays(3).isBefore(dataSaida)) {
            throw new Exception("A estadia não pode ser superior a 3 dias");
        }
    }

    private void validarAntecedenciaMaxima(LocalDate dataEntrada) throws Exception {
        LocalDate limiteAntecedencia = LocalDate.now().plusMonths(1);
        if (dataEntrada.isAfter(limiteAntecedencia)) {
            throw new Exception("A reserva não pode ser solicitada com mais de 30 dias de antecedência");
        }
    }
}







