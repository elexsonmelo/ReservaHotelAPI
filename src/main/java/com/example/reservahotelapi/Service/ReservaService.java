package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class  ReservaService {

    private final ReservaRepository reservaRepository;

    public void validarReserva(Reserva reserva) throws Exception {
        validarAntecedencia(reserva);
        validarDuracao(reserva.getDuracaoEmDias());
        validarDisponibilidadeQuarto(reserva);
    }
    private void validarDisponibilidadeQuarto(Reserva reserva) throws Exception {
        if (!reserva.getQuarto().getEstaDisponivel()) {
            throw new Exception("O quarto já está reservado para as datas solicitadas");
        }
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
    public void validarDuracao(int duracaoEmDias) throws Exception {
        if (duracaoEmDias > 3) {
            throw new Exception("A estadia não pode ser superior a 3 dias");
        }
    }

    public List<Reserva> verificarDisponibilidade(LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaRepository.findByDataEntradaBetween(dataEntrada, dataSaida);
    }

    public void cancelarReserva(Long reservaId){
    }

    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
    public Reserva modificarReserva(Long reservaId, Reserva reservaModificada) {
        return reservaModificada;
    }
}

