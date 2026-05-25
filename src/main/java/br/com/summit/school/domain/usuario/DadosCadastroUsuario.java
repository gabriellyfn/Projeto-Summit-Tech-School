package br.com.summit.school.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(

        @NotBlank
        String login,

        @NotBlank
        String senha,

       @NotNull
       Nome_Perfil perfil
){
}
