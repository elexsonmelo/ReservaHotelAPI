package Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "quartos")
public class Quarto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int codigo;
    @Column
    private double valor;
    @JoinColumn
    @OneToOne
    private Reserva reserva;
}

