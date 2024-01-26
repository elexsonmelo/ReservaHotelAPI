package Model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
    @Entity
    public class Reserva {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column
        private int idReserva;
        @Column
        private int idQuarto;
        @Column
        private int idCliente;
        @Column
        private double valor;
        @Column
        private LocalDate dataEntrada;
        @Column
        private LocalDate dataSaida;
    }




