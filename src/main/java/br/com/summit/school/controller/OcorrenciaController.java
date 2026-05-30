package br.com.summit.school.controller;

import br.com.summit.school.domain.ocorrencia.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
            @PageableDefault(size = 10) Pageable paginacao
    ){

        var page = service.listar(paginacao);

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
