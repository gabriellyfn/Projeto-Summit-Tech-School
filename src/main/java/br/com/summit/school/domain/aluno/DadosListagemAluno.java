package br.com.summit.school.domain.aluno;

import java.time.LocalDate;

public record DadosListagemAluno(
        Long id,
        String nome,
        String foto,
        LocalDate dataDeNascimento,
        String email,
        String telefone
) {
    public DadosListagemAluno(Aluno aluno){
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getFoto(),
                aluno.getDataDeNascimento(),
                aluno.getEmail(),
                aluno.getTelefone()
        );
    }
}
