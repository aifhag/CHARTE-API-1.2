package br.com.charte.backend.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "categoria",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nome_categoria")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nome_categoria", nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}