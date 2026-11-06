
package br.com.charte.backend.catalogo.service;

import br.com.charte.backend.catalogo.entity.Arte;
import br.com.charte.backend.catalogo.repository.ArteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArteBuscaService {

    private final ArteRepository arteRepository;

    public ArteBuscaService(ArteRepository arteRepository) {
        this.arteRepository = arteRepository;
    }

    public List<Arte> listarDisponiveis() {
        return arteRepository.findByDisponivelTrue();
    }

    public List<Arte> buscarPorArtista(Integer idArtista) {
        validarId(idArtista, "artista");

        return arteRepository.findByArtistaIdArtista(idArtista)
                .stream()
                .filter(arte -> Boolean.TRUE.equals(arte.getDisponivel()))
                .toList();
    }

    public List<Arte> buscarPorCategoria(Integer idCategoria) {
        validarId(idCategoria, "categoria");

        return arteRepository
                .findByCategoriaIdCategoriaAndDisponivelTrue(idCategoria);
    }

    public List<Arte> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe um título para pesquisar.");
        }

        return arteRepository
                .findByTituloContainingIgnoreCase(titulo.trim())
                .stream()
                .filter(arte -> Boolean.TRUE.equals(arte.getDisponivel()))
                .toList();
    }

    public List<Arte> buscarPorFaixaDePreco(
            BigDecimal minimo,
            BigDecimal maximo) {

        if (minimo == null || maximo == null) {
            throw new IllegalArgumentException(
                    "Informe o preço mínimo e o máximo.");
        }

        if (minimo.compareTo(BigDecimal.ZERO) < 0
                || maximo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Os preços não podem ser negativos.");
        }

        if (minimo.compareTo(maximo) > 0) {
            throw new IllegalArgumentException(
                    "O preço mínimo não pode ser maior que o máximo.");
        }

        return arteRepository
                .findByPrecoBetween(minimo, maximo)
                .stream()
                .filter(arte -> Boolean.TRUE.equals(arte.getDisponivel()))
                .toList();
    }

    public List<Arte> listarDisponiveisPorMenorPreco() {
        return arteRepository.findByDisponivelTrueOrderByPrecoAsc();
    }

    private void validarId(Integer id, String tipo) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "O ID do " + tipo + " deve ser válido.");
        }
    }
}
