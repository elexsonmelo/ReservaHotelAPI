package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Dto.QuartoDto;
import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public QuartoDto salvar(QuartoDto quartoDto) {
        Quarto quarto = mapToEntity(quartoDto);
        quarto = quartoRepository.save(quarto);
        return mapToDTO(quarto);
    }

    public QuartoDto buscarPorId(Long id) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
        return mapToDTO(quarto);
    }

    public List<QuartoDto> buscarTodos() {
        List<Quarto> quartos = quartoRepository.findAll();
        return quartos.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void deletar(Long id) {
        quartoRepository.deleteById(id);
    }

    public QuartoDto atualizar(Long id, QuartoDto quartoDto) {
        Quarto quarto = mapToEntity(quartoDto);
        quarto.setQuartoId(id);
        quarto = quartoRepository.save(quarto);
        return mapToDTO(quarto);
    }

    public boolean quartoDisponivel(QuartoDto quarto) throws Exception {
        if (quarto != null && !quarto.getEstaDisponivel()) {
            throw new Exception("Quarto selecionado não está disponível!");
        }
        return true;
    }

    public QuartoDto mapToDTO(Quarto quarto) {
        QuartoDto quartoDTO = new QuartoDto();
        quartoDTO.setId(quarto.getQuartoId());
        quartoDTO.setNumero(quarto.getNumero());
        quartoDTO.setEstaDisponivel(quarto.getEstaDisponivel());
        return quartoDTO;
    }

    public Quarto mapToEntity(QuartoDto quartoDto) {
        Quarto quarto = new Quarto();
        quarto.setQuartoId(quartoDto.getId());
        quarto.setNumero(quartoDto.getNumero());
        quarto.setEstaDisponivel(quartoDto.getEstaDisponivel());
        return quarto;
    }
}


