package br.com.summit.school.controller;

import br.com.summit.school.domain.aluno.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service){
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR_ADMINISTRATIVO')")
    public ResponseEntity<DadosListagemAluno> cadastrarAluno(
            @RequestBody @Valid DadosCadastroAluno dados,
            UriComponentsBuilder uriBuilder){

        Aluno alunoSalvo = service.cadastrar(dados);

        URI uri = uriBuilder.path("/alunos/{id}")
                .buildAndExpand(alunoSalvo.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosListagemAluno(alunoSalvo));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR_ADMINISTRATIVO')")
    public ResponseEntity<List<DadosListagemAluno>> findAll(){

        List<DadosListagemAluno> alunos = service.listarTodos();

        return ResponseEntity.ok(alunos);
    }
}
