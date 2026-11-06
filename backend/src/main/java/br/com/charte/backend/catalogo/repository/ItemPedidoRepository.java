package br.com.charte.backend.catalogo.repository;

import br.com.charte.backend.catalogo.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepository
        extends JpaRepository<ItemPedido, Integer> {

    List<ItemPedido> findByPedidoIdPedido(Integer idPedido);

    boolean existsByArteIdArte(Integer idArte);
}