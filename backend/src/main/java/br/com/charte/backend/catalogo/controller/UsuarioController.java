package br.com.charte.backend.catalogo.controller;

import br.com.charte.backend.catalogo.dto.usuario.UsuarioCadastroDTO;
import br.com.charte.backend.catalogo.entity.Usuario;
import br.com.charte.backend.catalogo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(
            @Valid @RequestBody UsuarioCadastroDTO dto) {

        Usuario usuario = usuarioService.cadastrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                usuarioService.buscarPorId(id)
        );
    }
}