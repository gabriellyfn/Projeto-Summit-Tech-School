package br.com.summit.school.controller;

import br.com.summit.school.domain.dashboard.DadosDashboard;
import br.com.summit.school.domain.ocorrencia.OcorrenciaRepository;
import org.springframework.format.annotation.DateTimeFormat;
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

    private final OcorrenciaRepository repository;

    public DashboardController(OcorrenciaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/estatisticas")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDENADOR')")
    public ResponseEntity<DadosDashboard> obterEstatisticas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        if (inicio == null || fim == null) {
            inicio = LocalDate.now().withDayOfMonth(1);
            fim = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        }

        var ranking = repository.findRankingAlunosReincidentes();
        var estatisticasCategoria = repository.findTotalPorCategoriaNoPeriodo(inicio, fim);

        var dadosDashboard = new DadosDashboard(ranking, estatisticasCategoria);

        return ResponseEntity.ok(dadosDashboard);
    }
}
