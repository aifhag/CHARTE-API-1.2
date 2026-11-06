
package br.com.charte.backend.catalogo.service;

import br.com.charte.backend.catalogo.dto.request.ArteRequestDTO;
import br.com.charte.backend.catalogo.entity.Arte;
import br.com.charte.backend.catalogo.entity.Artista;
import br.com.charte.backend.catalogo.entity.Categoria;
import br.com.charte.backend.catalogo.repository.ArteRepository;
import br.com.charte.backend.catalogo.repository.ArtistaRepository;
import br.com.charte.backend.catalogo.repository.CategoriaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ArteService {

    private final ArteRepository arteRepository;
    private final ArtistaRepository artistaRepository;
    private final CategoriaRepository categoriaRepository;

    public ArteService(
            ArteRepository arteRepository,
            ArtistaRepository artistaRepository,
            CategoriaRepository categoriaRepository) {
        this.arteRepository = arteRepository;
        this.artistaRepository = artistaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Arte cadastrar(ArteRequestDTO dto) {
        validarDados(dto);

        Artista artista = artistaRepository.findById(dto.getIdArtista())
                .orElseThrow(() ->
                        new IllegalArgumentException("Artista não encontrado."));

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() ->
                        new IllegalArgumentException("Categoria não encontrada."));

        Arte arte = new Arte();
        arte.setArtista(artista);
        arte.setCategoria(categoria);
        arte.setTitulo(dto.getTitulo());
        arte.setDescricao(dto.getDescricao());
        arte.setPreco(dto.getPreco());
        arte.setDisponivel(true);

        return arteRepository.save(arte);
    }

    @Transactional(readOnly = true)
    public List<Arte> listarTodas() {
        return arteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Arte> listarDisponiveis() {
        return arteRepository.findByDisponivelTrue();
    }

    @Transactional(readOnly = true)
    public List<Arte> listarPorArtista(Integer idArtista) {
        return arteRepository.findByArtistaIdArtista(idArtista);
    }

    @Transactional(readOnly = true)
    public List<Arte> listarPorCategoria(Integer idCategoria) {
        return arteRepository.findByCategoriaIdCategoria(idCategoria);
    }

    @Transactional(readOnly = true)
    public Arte buscarPorId(Integer id) {
        return arteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Arte não encontrada."));
    }

    @Transactional
    public Arte atualizar(Integer id, ArteRequestDTO dto) {
        validarDados(dto);

        Arte arte = buscarPorId(id);

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() ->
                        new IllegalArgumentException("Categoria não encontrada."));

        arte.setCategoria(categoria);
        arte.setTitulo(dto.getTitulo());
        arte.setDescricao(dto.getDescricao());
        arte.setPreco(dto.getPreco());

        return arteRepository.save(arte);
    }

    @Transactional
    public Arte alterarDisponibilidade(Integer id, Boolean disponivel) {
        if (disponivel == null) {
            throw new IllegalArgumentException(
                    "Informe se a arte está disponível."
            );
        }

        Arte arte = buscarPorId(id);
        arte.setDisponivel(disponivel);

        return arteRepository.save(arte);
    }

    @Transactional
    public void excluir(Integer id, Integer idArtista) {
        Arte arte = buscarPorId(id);

        if (idArtista == null
                || !java.util.Objects.equals(
                arte.getArtista().getIdArtista(),
                idArtista)) {
            throw new IllegalArgumentException(
                    "Você não tem permissão para excluir esta arte."
            );
        }

        arteRepository.delete(arte);
    }

    private void validarDados(ArteRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException(
                    "Os dados da arte são obrigatórios."
            );
        }

        if (dto.getIdArtista() == null || dto.getIdArtista() <= 0) {
            throw new IllegalArgumentException(
                    "Informe um artista válido."
            );
        }

        if (dto.getIdCategoria() == null || dto.getIdCategoria() <= 0) {
            throw new IllegalArgumentException(
                    "Informe uma categoria válida."
            );
        }

        if (dto.getTitulo() == null || dto.getTitulo().isBlank()) {
            throw new IllegalArgumentException(
                    "O título da arte é obrigatório."
            );
        }

        if (dto.getPreco() == null
                || dto.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Informe um preço válido, igual ou superior a zero."
            );
        }
    }
}
