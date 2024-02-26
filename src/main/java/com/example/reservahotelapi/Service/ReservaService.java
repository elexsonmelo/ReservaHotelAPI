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

    private final DataUtilService dataUtilService;

    public void fazerReserva(Reserva reserva) throws Exception {
        dataUtilService.validarDataEntrada(reserva);
        validarAntecedencia(reserva);
        dataUtilService.validarDuracao(reserva.getDuracaoEmDias());
        validarAntecedencia(reserva);
        validarDisponibilidadeQuarto(reserva);
        reservaRepository.save(reserva);
    }
    private void validarDisponibilidadeQuarto(Reserva reserva) throws Exception {
        if (!reserva.getQuarto().getEstaDisponivel()) {
            throw new Exception("O quarto já está reservado para as datas solicitadas.");
        }
    }
    public void validarAntecedencia(Reserva reserva) throws Exception {
        LocalDate hoje = LocalDate.now();
        if (reserva.getDataEntrada().isAfter(hoje.plusDays(30))) {
            throw new Exception("A reserva não pode ser solicitada com mais de 30 dias de antecedência.");
        }
    }
    public List<Reserva> verificarDisponibilidade(LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaRepository.findByDataEntradaBetween(dataEntrada, dataSaida);
    }

    public void cancelarReserva(Long reservaId){
    }

    public Reserva modificarReserva(Long reservaId, Reserva reservaModificada) {
        return reservaModificada;
    }
}

