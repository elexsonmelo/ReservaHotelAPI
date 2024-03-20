package com.example.reservahotelapi.unit;

import com.example.reservahotelapi.Dto.ClienteDto;
import com.example.reservahotelapi.Dto.QuartoDto;
import com.example.reservahotelapi.Dto.ReservaDto;
import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Repository.ReservaRepository;
import com.example.reservahotelapi.Service.ClienteService;
import com.example.reservahotelapi.Service.DataUtilService;
import com.example.reservahotelapi.Service.QuartoService;
import com.example.reservahotelapi.Service.ReservaService;
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
        reservaDto.setQuarto(quartoDto);
        reservaDto.setDataEntrada(LocalDate.now());
        reservaDto.setDataSaida(LocalDate.now().plusDays(2));
        when(quartoService.quartoDisponivel(quartoDto)).thenReturn(true);
        when(reservaRepository.save(any())).thenReturn(new Reserva());

        ReservaDto result = reservaService.fazerReserva(reservaDto);

        assertNotNull(result);
        verify(dataUtilService).validarData(reservaDto.getDataEntrada(), reservaDto.getDataSaida());
        verify(quartoService).quartoDisponivel(quartoDto);
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

        ReservaDto result = reservaService.modificarReserva(reservaId, reservaAtualizada);

        assertNotNull(result);
        verify(dataUtilService).validarData(reserva.getDataEntrada(), reserva.getDataSaida());
        verify(reservaRepository).save(any());
    }

    @Test
    void testCancelarReserva() throws Exception {

        Long reservaId = 1L;
        Reserva reserva = new Reserva();
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));

        reservaService.cancelarReserva(reservaId);

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

