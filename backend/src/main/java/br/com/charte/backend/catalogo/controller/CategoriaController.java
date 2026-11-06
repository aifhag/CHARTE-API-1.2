package br.com.charte.backend.catalogo.controller;

import br.com.charte.backend.catalogo.dto.request.CategoriaRequestDTO;
import br.com.charte.backend.catalogo.entity.Categoria;
import br.com.charte.backend.catalogo.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(
            CategoriaService categoriaService) {

        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(
            @RequestBody @Valid CategoriaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {

        return ResponseEntity.ok(
                categoriaService.listarTodas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                categoriaService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid CategoriaRequestDTO dto) {

        return ResponseEntity.ok(
                categoriaService.atualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        categoriaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}