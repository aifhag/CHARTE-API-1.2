package br.com.charte.backend.catalogo.controller;

import br.com.charte.backend.catalogo.dto.request.ArtistaRequestDTO;
import br.com.charte.backend.catalogo.dto.response.ArtistaResponseDTO;
import br.com.charte.backend.catalogo.service.ArtistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistas")
@RequiredArgsConstructor
public class ArtistaController {

    private final ArtistaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistaResponseDTO cadastrar(
            @RequestBody @Valid ArtistaRequestDTO dto) {

        return service.cadastrar(dto);
    }

    @GetMapping
    public List<ArtistaResponseDTO> listar() {

        return service.listar();
    }

    @GetMapping("/{id}")
    public ArtistaResponseDTO buscar(
            @PathVariable Integer id) {

        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable Integer id) {

        service.excluir(id);
    }
}