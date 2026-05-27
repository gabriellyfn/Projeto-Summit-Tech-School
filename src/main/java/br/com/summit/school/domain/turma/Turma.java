package br.com.summit.school.domain.turma;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Turma")
@Table(name = "Turma")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma")
    private Long idTurma;

    @Column(name = "nome_da_turma", nullable = false, length = 50)
    private String nomeDaTurma;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno")
    private Turno turno;

    @Column(name = "ano", nullable = false, length = 4)
    private String ano;

    @Enumerated(EnumType.STRING)
    @Column(name = "semestre")
    private Semestre semestre;

    public Turma(DadosCadastroTurma dados) {
        this.nomeDaTurma = dados.nome();
        this.turno = dados.turno();
        this.semestre = dados.semestre();
    }
}
