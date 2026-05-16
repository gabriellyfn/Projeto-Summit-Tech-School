package br.com.summit.school.controller;

import br.com.summit.school.model.Aluno;
import br.com.summit.school.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno){
        return ResponseEntity.ok(alunoRepository.save(aluno));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAllAlunos(){
        return ResponseEntity.ok(alunoRepository.findAll());
    }


}
