
package br.com.charte.backend.catalogo.service;

import br.com.charte.backend.catalogo.dto.request.*;
import br.com.charte.backend.catalogo.entity.Arte;
import br.com.charte.backend.catalogo.entity.ItemPedido;
import br.com.charte.backend.catalogo.entity.Pedido;
import br.com.charte.backend.catalogo.entity.Usuario;
import br.com.charte.backend.catalogo.enums.StatusPedido;
import br.com.charte.backend.catalogo.exception.RecursoNaoEncontradoException;
import br.com.charte.backend.catalogo.repository.ArteRepository;
import br.com.charte.backend.catalogo.repository.PedidoRepository;
import br.com.charte.backend.catalogo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArteRepository arteRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            UsuarioRepository usuarioRepository,
            ArteRepository arteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.arteRepository = arteRepository;
    }

    @Transactional
    public Pedido criar(PedidoRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Usuário não encontrado."
                        ));

        Arte arte = arteRepository.findById(dto.getIdArte())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Arte não encontrada."
                        ));

        if (!Boolean.TRUE.equals(arte.getDisponivel())) {
            throw new IllegalArgumentException(
                    "A obra não está disponível para compra."
            );
        }

        if (dto.getQuantidade() == null ||
                dto.getQuantidade() < 1) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser pelo menos 1."
            );
        }

        if (arte.getPreco() == null ||
                arte.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O preço da obra é inválido."
            );
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setValorTotal(BigDecimal.ZERO);

        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setArte(arte);
        item.setQuantidade(dto.getQuantidade());
        item.setPrecoUnitario(arte.getPreco());

        pedido.getItens().add(item);

        BigDecimal subtotal = item.calcularSubtotal();
        pedido.setValorTotal(subtotal);

        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Pedido não encontrado."
                        ));
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public Pedido cancelar(Integer id) {
        Pedido pedido = buscarPorId(id);

        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new IllegalArgumentException(
                    "Este pedido já está cancelado."
            );
        }

        if (pedido.getStatus() == StatusPedido.FINALIZADO) {
            throw new IllegalArgumentException(
                    "Não é possível cancelar um pedido finalizado."
            );
        }

        pedido.setStatus(StatusPedido.CANCELADO);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido alterarStatus(
            Integer id,
            StatusPedido novoStatus) {

        if (novoStatus == null) {
            throw new IllegalArgumentException(
                    "O status é obrigatório."
            );
        }

        Pedido pedido = buscarPorId(id);

        if (pedido.getStatus() == StatusPedido.CANCELADO ||
                pedido.getStatus() == StatusPedido.FINALIZADO) {
            throw new IllegalArgumentException(
                    "Não é possível alterar um pedido cancelado ou finalizado."
            );
        }

        if (novoStatus == StatusPedido.CANCELADO) {
            return cancelar(id);
        }

        pedido.setStatus(novoStatus);

        return pedidoRepository.save(pedido);
    }
}
