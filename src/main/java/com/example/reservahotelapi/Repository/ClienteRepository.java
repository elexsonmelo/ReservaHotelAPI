package com.example.reservahotelapi.Repository;

import com.example.reservahotelapi.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
