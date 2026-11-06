package br.com.charte.backend.catalogo.controller;

import br.com.charte.backend.catalogo.dto.request.ArteRequestDTO;
import br.com.charte.backend.catalogo.entity.Arte;
import br.com.charte.backend.catalogo.service.ArteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artes")
public class ArteController {

    private final ArteService arteService;

    public ArteController(ArteService arteService) {
        this.arteService = arteService;
    }

    @PostMapping
    public ResponseEntity<Arte> cadastrar(
            @RequestBody @Valid ArteRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(arteService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Arte>> listarTodas() {

        return ResponseEntity.ok(
                arteService.listarTodas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arte> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                arteService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Arte> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid ArteRequestDTO dto) {

        return ResponseEntity.ok(
                arteService.atualizar(id, dto)
        );
    }

    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Arte> alterarDisponibilidade(
            @PathVariable Integer id,
            @RequestParam Boolean disponivel) {

        return ResponseEntity.ok(
                arteService.alterarDisponibilidade(
                        id,
                        disponivel
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id,
            @RequestParam Integer idArtista) {

        arteService.excluir(id, idArtista);

        return ResponseEntity.noContent().build();
    }
}
