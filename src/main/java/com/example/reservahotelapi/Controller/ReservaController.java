package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Service.DataUtilService;
import com.example.reservahotelapi.Service.ReservaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Reserva> consultarReserva(@PathVariable int id) {
        Reserva reserva = reservaService.consultarReserva(id);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarReserva(@RequestBody Reserva reserva) {
        try {
            reservaService.modificarReserva(reserva);
            return ResponseEntity.ok("Reserva modificada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelarReserva(@PathVariable int id) {
        try {
            reservaService.cancelarReserva((long) id);
            return ResponseEntity.ok("Reserva cancelada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
