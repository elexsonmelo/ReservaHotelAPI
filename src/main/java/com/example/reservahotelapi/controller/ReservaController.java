package com.example.reservahotelapi.controller;

import com.example.reservahotelapi.dto.QuartoDto;
import com.example.reservahotelapi.dto.ReservaDto;
import com.example.reservahotelapi.service.ReservaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> findById(@PathVariable Long id) throws Exception {
        ReservaDto reserva = reservaService.consultarReserva(id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ReservaDto>> findAll() {
        List<ReservaDto> reservas = reservaService.listarReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }
    @PostMapping("/criar")
    public ResponseEntity<String> create(@RequestBody ReservaDto reservaDto) {
        try {
            reservaService.fazerReserva(reservaDto);
            return ResponseEntity.ok("Reserva realizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<ReservaDto> atualizarReserva(@PathVariable Long id, @RequestBody ReservaDto reservaDto) {
        try {
            ReservaDto reservaAtualizada = reservaService.atualizarReserva(id, reservaDto);
            return ResponseEntity.ok(reservaAtualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long reservaId) {
        try {
            reservaService.exluirReserva(reservaId);
            return ResponseEntity.ok("Reserva cancelada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
