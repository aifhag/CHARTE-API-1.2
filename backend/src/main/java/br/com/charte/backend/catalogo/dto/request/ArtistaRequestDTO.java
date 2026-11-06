package br.com.charte.backend.catalogo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArtistaRequestDTO {

    @NotBlank(message = "Nome artístico é obrigatório")
    private String nomeArtistico;

    private String biografia;

    private Integer idUsuario;
}