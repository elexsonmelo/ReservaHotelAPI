package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table (name = "reservas")
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int idReserva;
    @Column
    private int idQuarto;
    @Column
    private String idCliente;
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




