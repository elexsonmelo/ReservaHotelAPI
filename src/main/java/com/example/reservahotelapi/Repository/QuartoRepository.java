package com.example.reservahotelapi.Repository;

import com.example.reservahotelapi.Model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuartoRepository extends JpaRepository<Quarto, Integer> {
    List<Quarto> findByEstaDisponivelTrue();
}
