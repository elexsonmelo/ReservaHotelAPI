package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="endereco_clientes")
public class Endereco implements Serializable {
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
    @JoinColumn
    @ManyToOne
    private Cliente cliente;
}

