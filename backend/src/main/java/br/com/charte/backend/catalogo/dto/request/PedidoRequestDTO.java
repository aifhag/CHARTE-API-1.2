
package br.com.charte.backend.catalogo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PedidoRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Integer idUsuario;

    @NotNull(message = "O ID da arte é obrigatório.")
    private Integer idArte;

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
    private Integer quantidade;

    public PedidoRequestDTO() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdArte() {
        return idArte;
    }

    public void setIdArte(Integer idArte) {
        this.idArte = idArte;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
