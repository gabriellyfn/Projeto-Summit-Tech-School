package br.com.summit.school.model;

import jakarta.persistence.*;

@Entity(name = "Aluno_Turma")
@Table(name = "aluno_turma")

public class AlunoTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno_turma")
    private Long idAlunoTurma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turma")
    private Turma turma;

}
