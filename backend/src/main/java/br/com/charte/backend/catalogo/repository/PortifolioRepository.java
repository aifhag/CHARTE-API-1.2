package br.com.charte.backend.catalogo.repository;

import br.com.charte.backend.catalogo.entity.Portifolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortifolioRepository extends JpaRepository<Portifolio, Integer> {

    List<Portifolio> findByCategoriaIdCategoria(Integer idCategoria);

    List<Portifolio> findByArtistaIdArtista(Integer idArtista);

}