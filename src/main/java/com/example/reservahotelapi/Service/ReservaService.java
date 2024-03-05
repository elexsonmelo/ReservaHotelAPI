package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Quarto;
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
        validarQuartoDisponivel(reserva.getQuarto());
        reservaRepository.save(reserva);
    }
    private void validarQuartoDisponivel(Quarto quarto) throws Exception {
        if (Boolean.FALSE.equals(quarto.getEstaDisponivel())) {
            throw new Exception("O quarto já está reservado para as datas solicitadas");
        }
    }
    public List<Reserva> verificarDisponibilidade(LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaRepository.findByDataEntradaBetween(dataEntrada, dataSaida);
    }

    public void cancelarReserva(Long reservaId){
    }

    public Reserva modificarReserva(Reserva reservaModificada) {
        return reservaModificada;
    }

    public Reserva consultarReserva(int id) {
        return null;
    }
}

