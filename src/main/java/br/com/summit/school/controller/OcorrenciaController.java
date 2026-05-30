package br.com.summit.school.controller;

import br.com.summit.school.domain.ocorrencia.DadosCadastroOcorrencia;
import br.com.summit.school.domain.ocorrencia.DadosDetalhamentoOcorrencia;
import br.com.summit.school.domain.ocorrencia.DadosListagemOcorrencia;
import br.com.summit.school.domain.ocorrencia.OcorrenciaService;
import br.com.summit.school.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @PreAuthorize("hasAnyRole('PROFESSOR_ADMINISTRATIVO', 'ADMIN')")
    public ResponseEntity<DadosDetalhamentoOcorrencia> cadastrar(
            @RequestBody @Valid DadosCadastroOcorrencia dados,
            @AuthenticationPrincipal Usuario usuarioLogado,
            UriComponentsBuilder uriBuilder
            ){
        DadosDetalhamentoOcorrencia ocorrencia = service.cadastrar(dados, usuarioLogado.getId_usuario());

        URI uri = uriBuilder
                .path("/ocorrencias/{id}")
                .buildAndExpand(ocorrencia.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(ocorrencia);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('COORDENADOR', 'ANALISTA_DE_QUALIDADE', 'ADMIN')")
    public ResponseEntity<Page<DadosListagemOcorrencia>> listar(
            @PageableDefault(size = 10) Pageable paginacao
    ){

        var page = service.listar(paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENADOR', 'ANALISTA_DE_QUALIDADE', 'ADMIN')")
    public ResponseEntity<DadosDetalhamentoOcorrencia> detalhar(
            @PathVariable Long id
    ){

        var ocorrencia = service.detalhar(id);

        return ResponseEntity.ok(ocorrencia);
    }
}
