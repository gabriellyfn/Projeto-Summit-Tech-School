package br.com.summit.school.domain.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroAluno(

        @NotBlank(message = "O nome é obrigatório e não pode estar em branco")
        String nome,

        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataDeNascimento,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone
) {
}
