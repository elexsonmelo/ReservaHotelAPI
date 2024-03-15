package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Cliente;
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

    private final QuartoService quartoService;

    private final ClienteService clienteService;


    public Reserva fazerReserva(Reserva reserva) throws Exception {
        Cliente cliente = reserva.getCliente();
        Quarto quarto = reserva.getQuarto();
        LocalDate dataEntrada = reserva.getDataEntrada();
        LocalDate dataSaida = reserva.getDataSaida();

        dataUtilService.validarData(dataEntrada, dataSaida);

        if (!quartoService.quartoDisponivel(quarto)) {
            throw new Exception("Quarto não disponível para as datas selecionadas");
        }
        return reservaRepository.save(reserva);
    }

    public Reserva modificarReserva(Long reservaId, Reserva reservaAtualizada) throws Exception {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
        dataUtilService.validarData(reserva.getDataEntrada(), reserva.getDataSaida());
        reserva.setDataEntrada(reservaAtualizada.getDataEntrada());
        reserva.setDataSaida(reservaAtualizada.getDataSaida());
        return reservaRepository.save(reserva);
    }

    public void cancelarReserva(Long reservaId) throws Exception {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
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




