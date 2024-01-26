package Model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int idQuarto;
    @Column
    private int numero;
    @Column
    private double valor;
}

