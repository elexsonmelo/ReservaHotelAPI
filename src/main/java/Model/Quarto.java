package Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "quartos")
public class Quarto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID quartoId;
    @Column
    private String name;
    @Column(columnDefinition = "boolean default true")
    private Boolean estaDisponivel;
}

