package br.com.charte.backend.catalogo.mapper;

import br.com.charte.backend.catalogo.dto.response.UsuarioResponseDTO;
import br.com.charte.backend.catalogo.dto.request.*;
import br.com.charte.backend.catalogo.dto.response.*;
import br.com.charte.backend.catalogo.entity.Usuario;
import br.com.charte.backend.catalogo.enums.StatusConta;

import java.time.LocalDateTime;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto){

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setGenero(dto.getGenero());
        usuario.setTelefone(dto.getTelefone());
        usuario.setTipoUsuario(dto.getTipoUsuario());

        usuario.setStatusConta(StatusConta.Ativo);
        usuario.setDataCriacao(LocalDateTime.now());

        return usuario;
    }

    public static UsuarioResponseDTO toResponse(Usuario usuario){

        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setTipoUsuario(usuario.getTipoUsuario());
        dto.setStatusConta(usuario.getStatusConta());

        return dto;
    }
}
