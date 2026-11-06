package br.com.charte.backend.catalogo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ArteResponseDTO {

    private Integer idArte;
    private Integer idArtista;
    private String nomeArtistico;
    private Integer idCategoria;
    private String categoria;
    private String titulo;
    private String descricao;
    private BigDecimal preco;
    private Boolean disponivel;
}