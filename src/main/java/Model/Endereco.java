package Model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name ="enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String numero;
    @Column
    private String cep;
    @Column
    private String cidade;
    @Column
    private String uf;
}

