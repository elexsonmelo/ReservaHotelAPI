package com.example.reservahotelapi.service;

import com.example.reservahotelapi.dto.ClienteDto;
import com.example.reservahotelapi.dto.ReservaDto;
import com.example.reservahotelapi.model.Reserva;
import com.example.reservahotelapi.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ReservaDto fazerReserva(ReservaDto reservaDTO) throws Exception {
        ClienteDto cliente = reservaDTO.getCliente();
        Long quarto  = reservaDTO.getQuartoId();
        LocalDate dataEntrada = reservaDTO.getDataEntrada();
        LocalDate dataSaida = reservaDTO.getDataSaida();
        dataUtilService.validarData(dataEntrada, dataSaida);
        Reserva reserva = reservaRepository.save(mapToEntity(reservaDTO));
        return mapToDTO(reserva);
    }
    @Transactional
    public ReservaDto atualizarReserva(Long id, ReservaDto reservaDto) throws Exception {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
        dataUtilService.validarData(reservaDto.getDataEntrada(), reservaDto.getDataSaida());
        reserva.setCliente(clienteService.mapToEntity(reservaDto.getCliente()));
        reserva.setQuarto(quartoService.mapToEntity(quartoService.buscarPorId(reservaDto.getQuartoId())));
        reserva.setDataEntrada(reservaDto.getDataEntrada());
        reserva.setDataSaida(reservaDto.getDataSaida());
        Reserva reservaAtualizada = reservaRepository.save(reserva);
        return mapToDTO(reservaAtualizada);
    }

    public void exluirReserva(Long id) throws Exception {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
        reservaRepository.delete(reserva);
    }
    @Transactional(readOnly = true)
    public ReservaDto consultarReserva(Long id) throws Exception {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new Exception("Reserva não encontrada."));
        return mapToDTO(reserva);
    }

    @Transactional(readOnly = true)
    public List<ReservaDto> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private ReservaDto mapToDTO(Reserva reserva) {
        ReservaDto reservaDTO = new ReservaDto();
        reservaDTO.setId(reserva.getId());
        reservaDTO.setCliente(clienteService.mapToDto(reserva.getCliente()));
        reservaDTO.setQuartoId(reserva.getQuarto().getId());
        reservaDTO.setDataEntrada(reserva.getDataEntrada());
        reservaDTO.setDataSaida(reserva.getDataSaida());

        return reservaDTO;
    }

    private Reserva mapToEntity(ReservaDto reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setId(reservaDTO.getId());
        reserva.setCliente(clienteService.mapToEntity(reservaDTO.getCliente()));
        reserva.setQuarto(quartoService.mapToEntity(quartoService.buscarPorId(reservaDTO.getQuartoId())));
        reserva.setDataEntrada(reservaDTO.getDataEntrada());
        reserva.setDataSaida(reservaDTO.getDataSaida());
        return reserva;
    }
}




