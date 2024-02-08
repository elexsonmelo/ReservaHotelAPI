package com.example.reservahotelapi.Repository;

import com.example.reservahotelapi.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByDataEntradaBetween(LocalDate dataEntrada, LocalDate dataSaida);
}


