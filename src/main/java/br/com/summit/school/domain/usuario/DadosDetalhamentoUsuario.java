package br.com.summit.school.domain.usuario;

import br.com.summit.school.domain.perfil.Perfil;

import java.time.LocalDate;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        Perfil perfil,
        String email,
        String telefone,
        LocalDate dataNascimento,
        String foto) {

    public DadosDetalhamentoUsuario(Usuario usuario){
     this(
             usuario.getId_usuario(),
             usuario.getLogin(),
             usuario.getPerfil(),
             usuario.getEmail(),
             usuario.getTelefone(),
             usuario.getDataNascimento(),
             usuario.getFoto());
    }
}
