package br.com.charte.backend.catalogo.dto.response;

import lombok.Data;

@Data
public class ArtistaResponseDTO {

    private Integer idArtista;

    private Integer idUsuario;

    private String nomeArtistico;

    private String biografia;
}