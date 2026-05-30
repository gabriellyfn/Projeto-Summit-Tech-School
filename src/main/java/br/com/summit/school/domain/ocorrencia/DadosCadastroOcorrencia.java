package br.com.summit.school.domain.ocorrencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DadosCadastroOcorrencia(

        @NotNull(message = "O ID da turma é obrigatório")
        Long idTurma,

        @NotNull(message = "O ID do aluno é obrigatório")
        Long idAluno,

        @NotNull(message = "O ID do tipo de ocorrência é obrigatório")
        Long idTipoOcorrencia,

        @NotNull(message = "A data da ocorrência é obrigatória")
        LocalDate data,

        @NotNull(message = "A hora da ocorrência é obrigatória")
        LocalTime hora,

        @NotNull(message = "A categoria da ocorrência é obrigatória")
        Nome_Ocorrencia categoriaOcorrencia,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao
) {
}
