package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Reserva;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class DataUtilService {

    public class ValidadorReserva {

        private void validarReserva(Reserva reserva) throws Exception {
            validarDuracao(reserva);
            validarDisponibilidadeQuarto(reserva);
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

