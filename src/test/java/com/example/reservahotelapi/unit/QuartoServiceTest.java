package com.example.reservahotelapi.unit;

import com.example.reservahotelapi.Dto.QuartoDto;
import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Repository.QuartoRepository;
import com.example.reservahotelapi.Service.QuartoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuartoServiceTest {

    @Mock
    private QuartoRepository quartoRepository;

    @InjectMocks
    private QuartoService quartoService;

    private Quarto quarto;
    private QuartoDto quartoDto;

    @BeforeEach
    public void setUp() {
        quarto = new Quarto();
        quarto.setQuartoId(1L);
        quarto.setNumero(Integer.parseInt("101"));

        quartoDto = new QuartoDto();
        quartoDto.setId(1L);
        quartoDto.setNumero(Integer.parseInt("101"));
    }

    @Test
    void testCreate() {

        when(quartoRepository.save(any(Quarto.class))).thenReturn(quarto);

        QuartoDto createdQuartoDto = quartoService.salvar(quartoDto);

        assertNotNull(createdQuartoDto);
        assertEquals(quartoDto.getId(), createdQuartoDto.getId());
        assertEquals(quartoDto.getNumero(), createdQuartoDto.getNumero());
    }

    @Test
    void testBuscarPorId_QuartoExists() {

        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));

        QuartoDto foundQuartoDto = quartoService.buscarPorId(1L);

        assertNotNull(foundQuartoDto);
        assertEquals(quartoDto.getId(), foundQuartoDto.getId());
        assertEquals(quartoDto.getNumero(), foundQuartoDto.getNumero());
    }

    @Test
    void testBuscarTodos() {

        List<Quarto> quartos = Arrays.asList(quarto);
        when(quartoRepository.findAll()).thenReturn(quartos);

        List<QuartoDto> foundQuartosDto = quartoService.buscarTodos();

        assertNotNull(foundQuartosDto);
        assertEquals(1, foundQuartosDto.size());
        assertEquals(quartoDto.getId(), foundQuartosDto.get(0).getId());
        assertEquals(quartoDto.getNumero(), foundQuartosDto.get(0).getNumero());
    }

    @Test
    void testDeletar() {

        assertDoesNotThrow(() -> quartoService.deletar(1L));
    }

    @Test
    public void testAtualizar() {

        when(quartoRepository.save(any(Quarto.class))).thenReturn(quarto);

        QuartoDto updatedQuartoDto = quartoService.atualizar(1L, quartoDto);

        assertNotNull(updatedQuartoDto);
        assertEquals(quartoDto.getId(), updatedQuartoDto.getId());
        assertEquals(quartoDto.getNumero(), updatedQuartoDto.getNumero());
    }

    @Test
    void testQuartoDisponivel_ValidDto() {

        QuartoDto quartoDto = new QuartoDto();
        quartoDto.setEstaDisponivel(true);

        assertDoesNotThrow(() -> quartoService.quartoDisponivel(quartoDto));
    }

    @Test
    void testQuartoDisponivel_InvalidDto() {

        QuartoDto quartoDto = new QuartoDto();
        quartoDto.setEstaDisponivel(false);

        Exception exception = assertThrows(Exception.class, () -> quartoService.quartoDisponivel(quartoDto));
        assertEquals("Quarto selecionado não está disponível!", exception.getMessage());
    }

    @Test
    void testMapToDTO() {

        QuartoDto mappedDto = quartoService.mapToDTO(quarto);

        assertNotNull(mappedDto);
        assertEquals(quarto.getQuartoId(), mappedDto.getId());
        assertEquals(quarto.getNumero(), mappedDto.getNumero());
    }

    @Test
    void testMapToEntity() {

        Quarto mappedQuarto = quartoService.mapToEntity(quartoDto);

        assertNotNull(mappedQuarto);
        assertEquals(quartoDto.getId(), mappedQuarto.getQuartoId());
        assertEquals(quartoDto.getNumero(), mappedQuarto.getNumero());
    }
}

