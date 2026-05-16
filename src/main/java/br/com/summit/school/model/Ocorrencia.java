package br.com.summit.school.model;

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

import java.util.Date;
import java.util.TimeZone;

@Entity(name = "Ocorrencia")
@Table(name = "Ocorrencia")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode (of = "id_ocorrencia")
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
    private Uruario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_ocorrencia")
    private Tipo_Ocorrencia tipo_ocorrencia;

    @Column(name = "data")
    private Date data;

    @Column(name = "hora")
    private TimeZone hora;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_ocorrencia", length = 30)
    private Categoria_ocorrencia categoria_ocorrencia;

    @Column(name = "descricao", length = 255)
    private String descricao;

}
