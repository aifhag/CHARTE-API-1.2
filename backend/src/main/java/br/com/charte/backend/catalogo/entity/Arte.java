package br.com.charte.backend.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "arte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arte")
    private Integer idArte;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private Boolean disponivel = true;
}