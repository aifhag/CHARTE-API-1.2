package br.com.charte.backend.catalogo.dto.response;

import br.com.charte.backend.catalogo.enums.Genero;
import br.com.charte.backend.catalogo.enums.StatusConta;
import br.com.charte.backend.catalogo.enums.TipoUsuario;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UsuarioResponseDTO {

    private Integer id;

    private String nomeUsuario;

    private String email;

    private LocalDate dataNascimento;

    private Genero genero;

    private String telefone;

    private String fotoPerfil;

    private String bannerPerfil;

    private TipoUsuario tipoUsuario;

    private StatusConta statusConta;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    public void setId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNomeUsuario(String nomeUsuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setStatusConta(StatusConta statusConta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
