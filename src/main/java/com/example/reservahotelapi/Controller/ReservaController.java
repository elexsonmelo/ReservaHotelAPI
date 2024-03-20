package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Dto.ReservaDto;
import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Service.ReservaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") Long reservaId) {
        try {
            ReservaDto reserva = reservaService.consultarReserva(reservaId);
            return ResponseEntity.ok(reserva.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<String> findAll() {
        List<ReservaDto> reservas = reservaService.listarReservas();
        return ResponseEntity.ok(reservas.toString());
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
    public ResponseEntity<String> update(@PathVariable("id") Long reservaId, @RequestBody ReservaDto reservaAtualizada) {
        try {
            ReservaDto reservaDto = reservaService.modificarReserva(reservaId, reservaAtualizada);
            return ResponseEntity.ok(reservaDto.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long reservaId) {
        try {
            reservaService.cancelarReserva(reservaId);
            return ResponseEntity.ok("Reserva cancelada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
