package br.com.summit.school.domain.dashboard;

import java.util.List;

public record DadosDashboard(
        List<DadosRankingAluno> rankingReincidentes,
        List<DadosTotalCategoria> totalPorCategoria
) {
}
