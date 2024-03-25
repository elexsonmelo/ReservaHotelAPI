package com.example.reservahotelapi.unit;

import com.example.reservahotelapi.dto.ClienteDto;
import com.example.reservahotelapi.dto.QuartoDto;
import com.example.reservahotelapi.dto.ReservaDto;
import com.example.reservahotelapi.model.Reserva;
import com.example.reservahotelapi.repository.ReservaRepository;
import com.example.reservahotelapi.service.ClienteService;
import com.example.reservahotelapi.service.DataUtilService;
import com.example.reservahotelapi.service.QuartoService;
import com.example.reservahotelapi.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private DataUtilService dataUtilService;

    @Mock
    private QuartoService quartoService;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ReservaService reservaService;

    ReservaServiceTest() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFazerReserva() throws Exception {

        ReservaDto reservaDto = new ReservaDto();
        ClienteDto clienteDto = new ClienteDto();
        QuartoDto quartoDto = new QuartoDto();
        reservaDto.setCliente(clienteDto);
        reservaDto.setId(quartoDto.getId());
        reservaDto.setDataEntrada(LocalDate.now());
        reservaDto.setDataSaida(LocalDate.now().plusDays(2));
        when(reservaRepository.save(any())).thenReturn(new Reserva());

        ReservaDto result = reservaService.fazerReserva(reservaDto);

        assertNotNull(result);
        verify(dataUtilService).validarData(reservaDto.getDataEntrada(), reservaDto.getDataSaida());
        verify(reservaRepository).save(any());
    }

    @Test
    void testModificarReserva() throws Exception {

        Long reservaId = 1L;
        ReservaDto reservaAtualizada = new ReservaDto();
        reservaAtualizada.setDataEntrada(LocalDate.now());
        reservaAtualizada.setDataSaida(LocalDate.now().plusDays(3));
        Reserva reserva = new Reserva();
        reserva.setDataEntrada(LocalDate.now());
        reserva.setDataSaida(LocalDate.now().plusDays(2));
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any())).thenReturn(new Reserva());

        ReservaDto result = reservaService.atualizarReserva(reservaId, reservaAtualizada);

        assertNotNull(result);
        verify(dataUtilService).validarData(reserva.getDataEntrada(), reserva.getDataSaida());
        verify(reservaRepository).save(any());
    }

    @Test
    void testCancelarReserva() throws Exception {

        Long reservaId = 1L;
        Reserva reserva = new Reserva();
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));

        reservaService.exluirReserva(reservaId);

        verify(reservaRepository).findById(reservaId);
        verify(reservaRepository).delete(reserva);
    }

    @Test
    void testConsultarReserva() throws Exception {

        Long reservaId = 1L;
        Reserva reserva = new Reserva();
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));

        ReservaDto result = reservaService.consultarReserva(reservaId);

        assertNotNull(result);
        verify(reservaRepository).findById(reservaId);
    }

    @Test
    void testListarReservas() {

        List<Reserva> reservas = Arrays.asList(new Reserva(), new Reserva());
        when(reservaRepository.findAll()).thenReturn(reservas);

        List<ReservaDto> result = reservaService.listarReservas();

        assertNotNull(result);
        assertEquals(reservas.size(), result.size());
    }
}

