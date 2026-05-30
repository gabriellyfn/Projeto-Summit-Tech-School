package br.com.summit.school.domain.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTurma(
        @NotBlank(message = "O nome da turma é obrigatório")
        String nome,

        @NotNull(message = "O turno é obrigatório")
        Turno turno,

        @NotNull(message = "O semestre é obrigatório")
        Semestre semestre,

        @NotNull(message = "O ano é obrigatório")
        String ano
) {
}
