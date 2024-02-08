package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Model.Reserva;
import com.example.reservahotelapi.Service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
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
    @PostMapping
    public ResponseEntity<Reserva> create(@RequestBody Reserva reserva) {
        Reserva entity = reservaService.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
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
