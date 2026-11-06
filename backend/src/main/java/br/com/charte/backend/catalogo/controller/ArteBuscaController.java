
package br.com.charte.backend.catalogo.controller;

import br.com.charte.backend.catalogo.entity.Arte;
import br.com.charte.backend.catalogo.service.ArteBuscaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/artes")
public class ArteBuscaController {

    private final ArteBuscaService arteBuscaService;

    public ArteBuscaController(ArteBuscaService arteBuscaService) {
        this.arteBuscaService = arteBuscaService;
    }

    @GetMapping("/disponiveis")
    public List<Arte> listarDisponiveis() {
        return arteBuscaService.listarDisponiveis();
    }

    @GetMapping("/artista/{idArtista}")
    public List<Arte> buscarPorArtista(
            @PathVariable Integer idArtista) {
        return arteBuscaService.buscarPorArtista(idArtista);
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<Arte> buscarPorCategoria(
            @PathVariable Integer idCategoria) {
        return arteBuscaService.buscarPorCategoria(idCategoria);
    }

    @GetMapping("/buscar")
    public List<Arte> buscarPorTitulo(@RequestParam String titulo) {
        return arteBuscaService.buscarPorTitulo(titulo);
    }

    @GetMapping("/preco")
    public List<Arte> buscarPorPreco(
            @RequestParam BigDecimal minimo,
            @RequestParam BigDecimal maximo) {
        return arteBuscaService.buscarPorFaixaDePreco(minimo, maximo);
    }

    @GetMapping("/menor-preco")
    public List<Arte> listarPorMenorPreco() {
        return arteBuscaService.listarDisponiveisPorMenorPreco();
    }
}
