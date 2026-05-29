package br.com.summit.school.domain.dashboard;

public record DadosRankingAluno(
        Long alunoId,
        String nomeAluno,
        Long totalOcorrencias
) {
}
