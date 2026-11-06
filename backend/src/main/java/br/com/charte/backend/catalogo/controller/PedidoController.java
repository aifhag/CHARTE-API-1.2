package br.com.charte.backend.catalogo.controller;

import br.com.charte.backend.catalogo.dto.request.PedidoRequestDTO;
import br.com.charte.backend.catalogo.entity.Pedido;
import br.com.charte.backend.catalogo.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.charte.backend.catalogo.dto.AlterarStatusPedidoDTO;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @PatchMapping("/{id}/status")
    public ResponseEntity<Pedido> alterarStatus(
            @PathVariable Integer id,
            @RequestBody @Valid AlterarStatusPedidoDTO dto) {

        return ResponseEntity.ok(
                pedidoService.alterarStatus(
                        id,
                        dto.getStatus()
                )
        );
    }

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(
            @RequestBody @Valid PedidoRequestDTO dto) {

        Pedido pedido = pedidoService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                pedidoService.buscarPorId(id)
        );
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelar(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                pedidoService.cancelar(id)
        );
    }
}