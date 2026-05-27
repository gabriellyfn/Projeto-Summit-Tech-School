package br.com.summit.school.domain.usuario;

public record DadosListagemUsuario(
        Long id,
        String nome,
        String login,
        String email,
        String telefone,
        String foto,
        String perfil
) {
    public DadosListagemUsuario(Usuario usuario){
        this(
                usuario.getId_usuario(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getFoto(),
                usuario.getPerfil() != null ? usuario.getPerfil().getNome() : null
        );
    }
}
