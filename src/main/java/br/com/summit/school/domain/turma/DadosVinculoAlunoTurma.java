package br.com.summit.school.domain.turma;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record DadosVinculoAlunoTurma(
        @NotEmpty(message = "A lista de IDs de alunos não pode estar vazia")
        List<Long> alunosIds
) {
}
