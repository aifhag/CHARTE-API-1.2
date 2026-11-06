package br.com.charte.backend.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private Integer idArtista;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "nome_artistico", nullable = false, length = 100)
    private String nomeArtistico;

    @Column(columnDefinition = "TEXT")
    private String biografia;
}