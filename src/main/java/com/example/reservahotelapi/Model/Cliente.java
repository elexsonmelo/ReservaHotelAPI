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
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID clienteId;
    @Column
    private String nome;
    @Column
    private String cpf;
    @Column
    private String email;
    @JoinColumn
    @ManyToOne
    private Endereco endereco;
}





