package br.com.summit.school.controller;

import br.com.summit.school.domain.dashboard.DadosDashboard;
import br.com.summit.school.domain.ocorrencia.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @GetMapping("/estatisticas")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDENADOR')")
    public ResponseEntity<DadosDashboard> obterEstatisticas(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        var ranking = ocorrenciaRepository .findRankingAlunosReincidentes();
        var estatisticasCategoria = ocorrenciaRepository.findTotalPorCategoriaNoPeriodo(inicio, fim);

        var dadosDashboard = new DadosDashboard(ranking, estatisticasCategoria);

        return ResponseEntity.ok(dadosDashboard);
    }
}
