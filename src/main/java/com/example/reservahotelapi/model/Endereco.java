package com.example.reservahotelapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="endereco")
public class Endereco implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;
    @Column
    private String numero;
    @Column(nullable = false)
    private String cep;
    @Column
    private String cidade;
    @Column
    private String uf;
}

