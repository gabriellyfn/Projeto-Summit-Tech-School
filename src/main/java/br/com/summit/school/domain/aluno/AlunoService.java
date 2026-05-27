package br.com.summit.school.domain.aluno;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository){
        this.repository = repository;
    }

    @Transactional
    public Aluno cadastrar(DadosCadastroAluno dados) {

        Aluno aluno = new Aluno();

        aluno.setNome(dados.nome());
        aluno.setDataDeNascimento(dados.dataDeNascimento());
        aluno.setEmail(dados.email());
        aluno.setTelefone(dados.telefone());

        return repository.save(aluno);
    }

    public List<DadosListagemAluno> listarTodos(){

        return repository.findAll()
                .stream()
                .map(DadosListagemAluno::new)
                .toList();
    }
}
