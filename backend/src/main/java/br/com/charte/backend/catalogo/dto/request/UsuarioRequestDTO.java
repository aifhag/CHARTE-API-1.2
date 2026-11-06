package br.com.charte.backend.catalogo.dto.request;

import br.com.charte.backend.catalogo.enums.Genero;
import br.com.charte.backend.catalogo.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "Nome de usuário é obrigatório")
    private String nomeUsuario;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    private LocalDate dataNascimento;

    private Genero genero;

    private String telefone;

    private TipoUsuario tipoUsuario;

    public String getNomeUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getEmail() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LocalDate getDataNascimento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Genero getGenero() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTelefone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TipoUsuario getTipoUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}