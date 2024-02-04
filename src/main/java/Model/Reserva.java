package Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table (name = "reservas")
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private double valorReserva;
    @Column
    private LocalDate dataEntrada;
    @Column
    private LocalDate dataSaida;
    @JoinColumn
    @ManyToOne
    private Cliente cliente;
    @JoinColumn
    @ManyToOne
    private Quarto quarto;
}




