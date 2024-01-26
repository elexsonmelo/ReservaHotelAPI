package Controller;

import Model.Reserva;
import Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/disponibilidade")
    public List<Reserva> consultarDisponibilidade(@RequestParam LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaService.consultarDisponibilidade(dataEntrada, dataSaida);
    }
    @PostMapping("/fazer-reserva")
    public Reserva fazerReserva(@RequestBody Reserva reserva) {
        return reservaService.fazerReserva(reserva);
    }

    @PutMapping("/alterar-reserva/{reservaId}")
    public Reserva alterarReserva(@PathVariable Long reservaId, @RequestBody Reserva reservaModificada) {
        return reservaService.alterarReserva(reservaId, reservaModificada);
    }
    @DeleteMapping("/cancelar-reserva/{reservaId}")
    public void cancelarReserva(@PathVariable Long reservaId) {
        reservaService.cancelarReserva(reservaId);
    }
}
