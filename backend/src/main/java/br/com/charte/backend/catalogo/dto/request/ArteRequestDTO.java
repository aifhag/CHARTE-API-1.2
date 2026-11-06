package br.com.charte.backend.catalogo.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ArteRequestDTO {

    @NotNull(message = "O artista é obrigatório.")
    private Integer idArtista;

    @NotNull(message = "A categoria é obrigatória.")
    private Integer idCategoria;

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(
            value = "0.00",
            inclusive = true,
            message = "O preço não pode ser negativo."
    )
    private BigDecimal preco;

    public ArteRequestDTO() {
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
