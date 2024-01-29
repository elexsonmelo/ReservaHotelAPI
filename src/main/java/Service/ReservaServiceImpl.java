package Service;

import Model.Reserva;
import Repository.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ViacepService viacepService;

    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> consultarDisponibilidade(LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaRepository.findByDataEntradaBetween(dataEntrada, dataSaida);
    }

    @Override
    public Reserva fazerReserva(Reserva reserva) {
        return reserva;
    }

    @Override
    public void cancelarReserva(Long reservaId) {

    }

    @Override
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva alterarReserva(Long reservaId, Reserva reservaModificada) {

        return reservaModificada;
    }
}

