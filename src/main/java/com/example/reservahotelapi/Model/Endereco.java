package com.example.reservahotelapi.Model;

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
@Table(name ="endereco_cliente")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;
    @Column
    private String numero;
    @Column
    private String cep;
    @Column
    private String cidade;
    @Column
    private String uf;
}

