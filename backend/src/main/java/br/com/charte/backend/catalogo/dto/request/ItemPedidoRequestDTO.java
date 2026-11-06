package br.com.charte.backend.catalogo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ItemPedidoRequestDTO {

    @NotNull(message = "A arte é obrigatória.")
    private Integer idArte;

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
    private Integer quantidade;

    public ItemPedidoRequestDTO() {
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