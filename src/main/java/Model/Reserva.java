package Model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table (name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int numeroReserva;
    @Column
    private int numeroQuarto;
    @Column
    private int nomeCliente;
    @Column
    private double valorReserva;
    @Column
    private LocalDate dataEntrada;
    @Column
    private LocalDate dataSaida;
}




