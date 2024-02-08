package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Repository.QuartoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Quarto> getQuartoDisponivel() {
        return quartoRepository.findByEstaDisponivelTrue();
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

