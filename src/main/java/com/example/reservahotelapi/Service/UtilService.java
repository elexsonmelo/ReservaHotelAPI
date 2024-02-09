package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Reserva;
import org.springframework.stereotype.Service;

import java.time.LocalDate;



@Service
public class UtilService {

    public class ValidadorReserva {

        private void validarReserva(Reserva reserva) throws Exception {
            validarAntecedencia(reserva);
            validarDuracao(reserva);
            validarDisponibilidadeQuarto(reserva);
        }

        private void validarAntecedencia(Reserva reserva) throws Exception {
            LocalDate hoje = LocalDate.now();
            if (reserva.getDataEntrada().isBefore(hoje.plusDays(1))) {
                throw new Exception("A reserva deve ser feita com no mínimo 1 dia de antecedência");
            }
            if (reserva.getDataEntrada().isAfter(hoje.plusDays(30))) {
                throw new Exception("A reserva não pode ser solicitada com mais de 30 dias de antecedência");
            }
        }

        private void validarDuracao(Reserva reserva) throws Exception {
            if (reserva.getDuracaoEmDias() > 3) {
                throw new Exception("A estadia não pode ser superior a 3 dias");
            }
        }

        private void validarDisponibilidadeQuarto(Reserva reserva) throws Exception {
            if (!reserva.getQuarto().getEstaDisponivel()) {
                throw new Exception("O quarto já está reservado para as datas solicitadas");
            }
        }
    }
}

