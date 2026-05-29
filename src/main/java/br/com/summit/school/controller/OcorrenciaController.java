package br.com.summit.school.controller;

import br.com.summit.school.domain.ocorrencia.DadosCadastroOcorrencia;
import br.com.summit.school.domain.ocorrencia.DadosDetalhamentoOcorrencia;
import br.com.summit.school.domain.ocorrencia.DadosListagemOcorrencia;
import br.com.summit.school.domain.ocorrencia.OcorrenciaService;
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
    @PreAuthorize("hasRole('PROFESSOR_ADMINISTRATIVO', 'ADMIN')")
    public ResponseEntity<DadosDetalhamentoOcorrencia> cadastrar(
            @RequestBody @Valid DadosCadastroOcorrencia dados,
            UriComponentsBuilder uriBuilder
            ){
        DadosDetalhamentoOcorrencia ocorrencia =
                service.cadastrar(dados);

        URI uri = uriBuilder
                .path("/ocorrencias/{id}")
                .buildAndExpand(ocorrencia.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(ocorrencia);
    }

    @GetMapping
    @PreAuthorize("hasRole('COORDENADOR', 'ANALISTA_DE_QUALIDADE')")
    public ResponseEntity<Page<DadosListagemOcorrencia>> listar(
            @PageableDefault(size = 10) Pageable paginacao
    ){

        var page = service.listar(paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COORDENADOR', 'ANALISTA_DE_QUALIDADE')")
    public ResponseEntity<DadosDetalhamentoOcorrencia> detalhar(
            @PathVariable Long id
    ){

        var ocorrencia = service.detalhar(id);

        return ResponseEntity.ok(ocorrencia);
    }
}
