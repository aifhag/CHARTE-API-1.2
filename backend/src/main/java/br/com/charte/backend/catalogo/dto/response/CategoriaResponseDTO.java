package br.com.charte.backend.catalogo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaResponseDTO {

    private Integer idCategoria;
    private String nome;
    private String descricao;
}