package br.com.charte.backend.catalogo.repository;

import br.com.charte.backend.catalogo.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Integer> {

    boolean existsByNome(String nome);

    Optional<Categoria> findByNome(String nome);
}