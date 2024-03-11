package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Dto.QuartoDTO;
import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public Quarto salvar(Quarto quarto) {
        return quartoRepository.save(quarto);
    }
    public QuartoDTO buscarPorId(Long id) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
        return converterParaDTO(quarto);
    }
    public List<QuartoDTO> buscarTodos() {
        List<Quarto> quartos = quartoRepository.findAll();
        return quartos.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
    private QuartoDTO converterParaDTO(Quarto quarto) {
        QuartoDTO quartoDTO = new QuartoDTO();
        quartoDTO.setId(quarto.getQuartoId());
        quartoDTO.setNumero(quarto.getNumero());
        quartoDTO.setEstaDisponivel(quarto.getEstaDisponivel());
        return quartoDTO;
    }
    public void delete(Long id) {
        quartoRepository.deleteById(id);
    }
    public QuartoDTO update(Long id, QuartoDTO quartoDTO) {
        return quartoDTO;
    }
}


