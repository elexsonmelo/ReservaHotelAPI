package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void delete(Integer id) {
        quartoRepository.deleteById(id);
    }

    public Optional<Quarto> buscarPorId(Integer id) {
       return quartoRepository.findById(id);
    }
}


