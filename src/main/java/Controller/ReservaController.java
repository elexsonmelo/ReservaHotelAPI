package Controller;

import Model.Reserva;
import Service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/disponibilidade")
    public List<Reserva> consultarDisponibilidade(@RequestBody LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaService.consultarDisponibilidade(dataEntrada, dataSaida);
    }
    @PostMapping("/fazer-reserva")
    public ResponseEntity<Reserva> create(@RequestBody Reserva reserva) {
        Reserva reserva1 = reservaService.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reserva1);
    }

    @PutMapping("/alterar-reserva/{Id}")
    public Reserva alterarReserva(@PathVariable Long reservaId, @RequestBody Reserva reservaModificada) {
        return reservaService.alterarReserva(reservaId, reservaModificada);
    }
    @DeleteMapping("/cancelar-reserva/{Id}")
    public void cancelarReserva(@PathVariable Long reservaId) {
        reservaService.cancelarReserva(reservaId);
    }
}
