package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public Quarto salvar(Quarto quarto) {
        return quartoRepository.save(quarto);

    }

    public List<Quarto> buscarTodos() {
        return quartoRepository.findAll();
    }

    public Quarto buscarPorId(Integer id) {
        return quartoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Id nao encontrado.")
        );
    }

    public void delete(Integer id) {
        quartoRepository.deleteById(id);
    }
}


