package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Repository.ReservaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class  ReservaService {


    private final ReservaRepository reservaRepository;

    public boolean haQuartoDisponivel(LocalDate dataEntrada, LocalDate dataSaida) {
        List<Reserva> reservas = reservaRepository.findByDataEntradaBetween(dataEntrada, dataSaida);
        return reservas.isEmpty();
    }


    public List<Reserva> verificarDisponibilidade(LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaRepository.findByDataEntradaBetween(dataEntrada, dataSaida);
    }
    public Reserva fazerReserva(Reserva reserva){
        return reserva;
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

