package br.com.summit.school.domain.turma;

import br.com.summit.school.domain.aluno.Aluno;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Aluno_Turma")
@Table(name = "Aluno_Turma")
@Data
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
