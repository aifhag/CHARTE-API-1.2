
package br.com.charte.backend.catalogo.repository;

import br.com.charte.backend.catalogo.entity.Arte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ArteRepository
        extends JpaRepository<Arte, Integer> {

    List<Arte> findByArtistaIdArtista(Integer idArtista);

    List<Arte> findByCategoriaIdCategoria(Integer idCategoria);

    List<Arte> findByDisponivelTrue();

    List<Arte> findByTituloContainingIgnoreCase(String titulo);

    List<Arte> findByPrecoBetween(
            BigDecimal precoMinimo,
            BigDecimal precoMaximo);

    List<Arte> findByCategoriaIdCategoriaAndDisponivelTrue(
            Integer idCategoria);

    List<Arte> findByDisponivelTrueOrderByPrecoAsc();
}
