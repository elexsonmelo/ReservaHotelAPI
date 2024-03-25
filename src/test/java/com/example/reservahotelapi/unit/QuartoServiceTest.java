package com.example.reservahotelapi.unit;

import com.example.reservahotelapi.dto.QuartoDto;
import com.example.reservahotelapi.model.Quarto;
import com.example.reservahotelapi.repository.QuartoRepository;
import com.example.reservahotelapi.service.QuartoService;
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
        quarto.setId(1L);
        quarto.setNumero("101");

        quartoDto = new QuartoDto();
        quartoDto.setId(1L);
        quartoDto.setNumero("101");
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
    public void testExcluirQuartoExistente() throws Exception {

        Long id = 1L;
        Quarto quarto = new Quarto();
        when(quartoRepository.findById(id)).thenReturn(Optional.of(quarto));

        quartoService.excluir(id);

        verify(quartoRepository, times(1)).delete(quarto);
    }

    @Test
    public void testExcluirQuartoInexistente() {

        Long id = 2L;
        when(quartoRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            quartoService.excluir(id);
        });

        assertEquals("Quarto não encontrado.", exception.getMessage());
        verify(quartoRepository, never()).delete(any());
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
    void testMapToDTO() {

        QuartoDto mappedDto = quartoService.mapToDTO(quarto);

        assertNotNull(mappedDto);
        assertEquals(quarto.getId(), mappedDto.getId());
        assertEquals(quarto.getNumero(), mappedDto.getNumero());
    }

    @Test
    void testMapToEntity() {

        Quarto mappedQuarto = quartoService.mapToEntity(quartoDto);

        assertNotNull(mappedQuarto);
        assertEquals(quartoDto.getId(), mappedQuarto.getId());
        assertEquals(quartoDto.getNumero(), mappedQuarto.getNumero());
    }
}

