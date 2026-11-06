package br.com.charte.backend.catalogo.repository;

import br.com.charte.backend.catalogo.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository
        extends JpaRepository<Artista, Integer> {

    Optional<Artista> findByUsuarioId(Integer idUsuario);
}