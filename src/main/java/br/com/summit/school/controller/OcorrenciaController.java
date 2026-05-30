package br.com.summit.school.controller;


import br.com.summit.school.domain.ocorrencia.DadosAtualizacaoOcorrencia;
import br.com.summit.school.domain.ocorrencia.DadosCadastroOcorrencia;
import br.com.summit.school.domain.ocorrencia.DadosDetalhamentoOcorrencia;
import br.com.summit.school.domain.ocorrencia.DadosListagemOcorrencia;
import br.com.summit.school.domain.ocorrencia.OcorrenciaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaService service;

    public OcorrenciaController(OcorrenciaService service){
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PROFESSOR_ADMINISTRATIVO', 'ADMIN', 'PROFESSOR')")
    public ResponseEntity<DadosDetalhamentoOcorrencia> cadastrar(
            @RequestBody @Valid DadosCadastroOcorrencia dados,
            UriComponentsBuilder uriBuilder
            ){
        DadosDetalhamentoOcorrencia ocorrencia = service.cadastrar(dados);

        URI uri = uriBuilder
                .path("/ocorrencias/{id}")
                .buildAndExpand(ocorrencia.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(ocorrencia);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'COORDENADOR', 'ANALISTA_DE_QUALIDADE', 'ADMIN')")
    public ResponseEntity<Page<DadosListagemOcorrencia>> listar(
            @RequestParam(required = false) Long idAluno,
            @RequestParam(required = false) Long idTurma,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(required = false) Long idCategoria,
            @PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {

        var page = service.listar(idAluno, idTurma, dataInicio, dataFim, idCategoria, paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'COORDENADOR', 'ANALISTA_DE_QUALIDADE', 'ADMIN')")
    public ResponseEntity<DadosDetalhamentoOcorrencia> detalhar(
            @PathVariable Long id
    ){

        var ocorrencia = service.detalhar(id);

        return ResponseEntity.ok(ocorrencia);
    }

    @GetMapping("/alunos/{idAluno}")
    public ResponseEntity<Page<DadosListagemOcorrencia>> listarHistoricoPorAluno(
            @PathVariable Long idAluno,
            @PageableDefault(size = 10, sort = {"data"}, direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable paginacao) {

                var page = service.listarHistoricoPorAluno(idAluno, paginacao);
                return ResponseEntity.ok(page);
      
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ADMIN')")
    public ResponseEntity<DadosDetalhamentoOcorrencia> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoOcorrencia dados
    ) {

        var ocorrencia = service.atualizar(id, dados);

        return ResponseEntity.ok(ocorrencia);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ADMIN')")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
