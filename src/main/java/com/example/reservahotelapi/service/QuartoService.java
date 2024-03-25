package com.example.reservahotelapi.service;

import com.example.reservahotelapi.dto.QuartoDto;
import com.example.reservahotelapi.model.Quarto;
import com.example.reservahotelapi.repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;
    @Transactional
    public QuartoDto salvar(QuartoDto quartoDto) {
        Quarto quarto = mapToEntity(quartoDto);
        quarto = quartoRepository.save(quarto);
        return mapToDTO(quarto);
    }
    @Transactional(readOnly = true)
    public QuartoDto buscarPorId(Long id) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado."));
        return mapToDTO(quarto);
    }
    @Transactional(readOnly = true)
    public List<QuartoDto> buscarTodos() {
        List<Quarto> quartos = quartoRepository.findAll();
        return quartos.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional
    public QuartoDto atualizar(Long id, QuartoDto quartoDto) {
        Quarto quarto = mapToEntity(quartoDto);
        quarto.setId(id);
        quarto = quartoRepository.save(quarto);
        return mapToDTO(quarto);
    }
    public void excluir(Long id) throws Exception {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new Exception("Quarto não encontrado."));
        quartoRepository.delete(quarto);
    }



    public QuartoDto mapToDTO(Quarto quarto) {
        QuartoDto quartoDTO = new QuartoDto();
        quartoDTO.setId(quarto.getId());
        quartoDTO.setNumero(quarto.getNumero());
        return quartoDTO;
    }

    public Quarto mapToEntity(QuartoDto quartoDto) {
        Quarto quarto = new Quarto();
        quarto.setId(quartoDto.getId());
        quarto.setNumero(quartoDto.getNumero());
        return quarto;
    }
}


