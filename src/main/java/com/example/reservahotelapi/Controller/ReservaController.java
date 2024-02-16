package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Service.ReservaService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/disponibilidade")
    public List<Reserva> consultarDisponibilidade(@RequestParam String dataInicio, @RequestParam String dataFim) {
        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);
        return reservaService.verificarDisponibilidade(inicio, fim);
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
    @PutMapping("/alterar-reserva/{Id}")
    public Reserva alterarReserva(@PathVariable Long reservaId, @RequestBody Reserva reservaModificada) {
        return reservaService.modificarReserva(reservaId, reservaModificada);
    }
    @DeleteMapping("/deletar-reserva/{Id}")
    public void cancelarReserva(@PathVariable Long reservaId) {
        reservaService.cancelarReserva(reservaId);
    }
}
