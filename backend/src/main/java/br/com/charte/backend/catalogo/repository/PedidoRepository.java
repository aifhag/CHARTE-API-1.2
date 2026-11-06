package br.com.charte.backend.catalogo.repository;

import br.com.charte.backend.catalogo.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository
        extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByUsuarioId(Integer idCliente);
}