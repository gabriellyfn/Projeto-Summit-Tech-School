package br.com.summit.school.domain.usuario;

import br.com.summit.school.domain.perfil.Nome_Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(

        @NotBlank
        String login,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha,

        @NotNull
        Nome_Perfil perfil
){
}
