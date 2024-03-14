package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Dto.QuartoDto;
import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Repository.QuartoRepository;
import com.example.reservahotelapi.Repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public Quarto salvar(Quarto quarto) {
        return quartoRepository.save(quarto);
    }
    public QuartoDto buscarPorId(Long id) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
        return converterParaDTO(quarto);
    }
    public List<QuartoDto> buscarTodos() {
        List<Quarto> quartos = quartoRepository.findAll();
        return quartos.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
    private QuartoDto converterParaDTO(Quarto quarto) {
        QuartoDto quartoDTO = new QuartoDto();
        quartoDTO.setId(quarto.getQuartoId());
        quartoDTO.setNumero(quarto.getNumero());
        return quartoDTO;
    }
    public void deletar(Long id) {
        quartoRepository.deleteById(id);
    }
    public QuartoDto atualizar(Long id, QuartoDto quartoDTO) {
        return quartoDTO;
    }
    public boolean quartoDisponivel(Quarto quarto) throws Exception {
        if (quarto != null && !quarto.getEstaDisponivel()){
            throw new Exception("Quarto selecionado nao esta disponivel!");
        }
        return quarto.getEstaDisponivel();
    }
}


