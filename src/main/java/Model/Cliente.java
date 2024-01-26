package Model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String cpf;
    @Column
    private String email;
    @Column
    private String telefone;
}





