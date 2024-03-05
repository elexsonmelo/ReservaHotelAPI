package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Service.ReservaService;
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
    public ResponseEntity<String> consultarReserva(@PathVariable("id") Long reservaId) {
        try {
            Reserva reserva = reservaService.consultarReserva(reservaId);
            return ResponseEntity.ok(reserva.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<String> listarReservas() {
        List<Reserva> reservas = reservaService.listarReservas();
        return ResponseEntity.ok(reservas.toString());
    }
    @PostMapping("/criar")
    public ResponseEntity<String> criarReserva(@RequestBody Reserva reserva) {
        try {
            reservaService.fazerReserva(reserva);
            return ResponseEntity.ok("Reserva realizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarReserva(@PathVariable("id") Long reservaId, @RequestBody Reserva reservaAtualizada) {
        try {
            Reserva reserva = reservaService.modificarReserva(reservaId, reservaAtualizada);
            return ResponseEntity.ok(reserva.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelarReserva(@PathVariable("id") Long reservaId) {
        try {
            reservaService.cancelarReserva(reservaId);
            return ResponseEntity.ok("Reserva cancelada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
