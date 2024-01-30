package Service;

import Model.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

    Reserva fazerReserva(Reserva reserva);
    List<Reserva> consultarDisponibilidade(LocalDate dataInicio, LocalDate dataFim);
    Reserva alterarReserva(Long reservaId, Reserva reservaModificada);
    void cancelarReserva(Long reservaId);
    Reserva save(Reserva reserva);
}

