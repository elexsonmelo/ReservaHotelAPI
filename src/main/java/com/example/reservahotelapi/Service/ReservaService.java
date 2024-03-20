package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Dto.ClienteDto;
import com.example.reservahotelapi.Dto.QuartoDto;
import com.example.reservahotelapi.Dto.ReservaDto;
import com.example.reservahotelapi.Model.Cliente;
import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    private final DataUtilService dataUtilService;

    private final QuartoService quartoService;

    private final ClienteService clienteService;


    public ReservaDto fazerReserva(ReservaDto reservaDTO) throws Exception {
        ClienteDto cliente = reservaDTO.getCliente();
        QuartoDto quarto = reservaDTO.getQuarto();
        LocalDate dataEntrada = reservaDTO.getDataEntrada();
        LocalDate dataSaida = reservaDTO.getDataSaida();
        dataUtilService.validarData(dataEntrada, dataSaida);

        if (!quartoService.quartoDisponivel(quarto)) {
            throw new Exception("Quarto não disponível para as datas selecionadas");
        }

        Reserva reserva = reservaRepository.save(mapToEntity(reservaDTO));
        return mapToDTO(reserva);
    }

    public ReservaDto modificarReserva(Long reservaId, ReservaDto reservaAtualizada) throws Exception {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
        dataUtilService.validarData(reserva.getDataEntrada(), reserva.getDataSaida());

        reserva.setDataEntrada(reservaAtualizada.getDataEntrada());
        reserva.setDataSaida(reservaAtualizada.getDataSaida());

        Reserva updatedReserva = reservaRepository.save(reserva);
        return mapToDTO(updatedReserva);
    }

    public void cancelarReserva(Long reservaId) throws Exception {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
        reservaRepository.delete(reserva);
    }

    public ReservaDto consultarReserva(Long reservaId) throws Exception {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
        return mapToDTO(reserva);
    }

    public List<ReservaDto> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(this::mapToDTO).collect(Collectors.<ReservaDto>toList());
    }

    private ReservaDto mapToDTO(Reserva reserva) {
        ReservaDto reservaDTO = new ReservaDto();
        reservaDTO.setId(reserva.getId());
        reservaDTO.setCliente(clienteService.mapToDto(reserva.getCliente()));
        reservaDTO.setQuarto(quartoService.mapToDTO(reserva.getQuarto()));
        reservaDTO.setDataEntrada(reserva.getDataEntrada());
        reservaDTO.setDataSaida(reserva.getDataSaida());
        return reservaDTO;
    }

    private Reserva mapToEntity(ReservaDto reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setId(reservaDTO.getId());
        reserva.setCliente(clienteService.mapToEntity(reservaDTO.getCliente()));
        reserva.setQuarto(quartoService.mapToEntity(reservaDTO.getQuarto()));
        reserva.setDataEntrada(reservaDTO.getDataEntrada());
        reserva.setDataSaida(reservaDTO.getDataSaida());
        return reserva;
    }
}




