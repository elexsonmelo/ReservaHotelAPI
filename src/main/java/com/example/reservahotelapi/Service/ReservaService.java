package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Repository.QuartoRepository;
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

    private final QuartoRepository quartoRepository;

    public Reserva fazerReserva(Reserva reserva) throws Exception {
        dataUtilService.validarData(reserva);
        List<Quarto> quartosDisponiveis = quartoRepository.findByDisponivelTrueAndNotReservedBetween(
                reserva.getDataEntrada(), reserva.getDataSaida());

        if (quartosDisponiveis.isEmpty()) {
            throw new Exception("Não há quartos disponíveis para o período da reserva.");
        }
        Quarto quarto = quartosDisponiveis.get(0);
        reserva.setQuarto(quarto);
        return reservaRepository.save(reserva);
    }

    public Reserva modificarReserva(Long reservaId, Reserva reservaAtualizada) throws Exception {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
        dataUtilService.validarData(reserva);
        reserva.setDataEntrada(reservaAtualizada.getDataEntrada());
        reserva.setDataSaida(reservaAtualizada.getDataSaida());
        return reservaRepository.save(reserva);
    }

    public void cancelarReserva(Long reservaId) throws Exception {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
        reserva.getQuarto().setEstaDisponivel(true);
        reservaRepository.delete(reserva);
    }

    public Reserva consultarReserva(Long reservaId) throws Exception {
        return reservaRepository.findById(reservaId)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
}


