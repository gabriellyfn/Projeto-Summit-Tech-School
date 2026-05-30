package br.com.summit.school.controller;

import br.com.summit.school.domain.turma.DadosCadastroTurma;
import br.com.summit.school.domain.turma.DadosListagemTurma;
import br.com.summit.school.domain.turma.DadosVinculoAlunoTurma;
import br.com.summit.school.domain.turma.Turma;
import br.com.summit.school.domain.turma.TurmaRepository;
import br.com.summit.school.domain.turma.TurmaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('PROFESSOR_ADMINISTRATIVO', 'ADMIN')")
    public ResponseEntity<DadosListagemTurma> cadastrar(@RequestBody @Valid DadosCadastroTurma dados, UriComponentsBuilder uriBuilder) {
        var turma = new Turma(dados);
        repository.save(turma);

        URI uri = uriBuilder.path("/turmas/{id}").buildAndExpand(turma.getIdTurma()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTurma>> listar(@PageableDefault(size = 10, sort = {"nomeDaTurma"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemTurma::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/{id}/vincular-alunos")
    @PreAuthorize("hasAnyRole('PROFESSOR_ADMINISTRATIVO', 'ADMIN')")
    public ResponseEntity<String> vincularAlunos(@PathVariable Long id, @RequestBody @Valid DadosVinculoAlunoTurma dados) {
        turmaService.vincularAlunos(id, dados);
        return ResponseEntity.ok("Alunos vinculados com sucesso à turma " + id);
    }
}
