package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Repository.ReservaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class ReservaServiceImpl implements ReservaService {


    private final ReservaRepository reservaRepository;

    @Override
    public List<Reserva> verificarDisponibilidade(LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaRepository.findByDataEntradaBetween(dataEntrada, dataSaida);
    }

    @Override
    public Reserva fazerReserva(Reserva reserva){
        return reserva;
    }

    @Override
    public void cancelarReserva(Long reservaId){

    }

    @Override
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva modificarReserva(Long reservaId, Reserva reservaModificada) {
        return reservaModificada;
    }
}

