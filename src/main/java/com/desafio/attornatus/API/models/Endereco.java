package com.desafio.attornatus.API.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String logradouro;

    @Column(length = 100, nullable = false)
    private String cep;

    @Column(length = 100, nullable = false)
    private Integer numero;

    @Column(length = 100, nullable = false)
    private String cidade;

    @Column(nullable = false)
    private boolean principal;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference
    @JsonIgnoreProperties({"enderecos"})
    private Pessoa pessoa;
}

