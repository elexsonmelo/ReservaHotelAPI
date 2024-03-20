package com.example.reservahotelapi.Repository;

import com.example.reservahotelapi.Model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
}
