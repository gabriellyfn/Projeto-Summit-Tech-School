package br.com.summit.school.domain.ocorrencia;

import br.com.summit.school.domain.aluno.Aluno;
import br.com.summit.school.domain.usuario.Usuario;
import br.com.summit.school.domain.turma.Turma;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Ocorrencia")
@Table(name = "Ocorrencia")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id_ocorrencia")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ocorrencia")
    private Long id_ocorrencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_ocorrencia")
    private br.com.summit.school.domain.ocorrencia.Tipo_Ocorrencia tipo_ocorrencia;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "hora")
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_ocorrencia", length = 30)
    private br.com.summit.school.model.Tipo_Ocorrencia categoria_ocorrencia;

    @Column(name = "descricao", length = 255)
    private String descricao;

}
