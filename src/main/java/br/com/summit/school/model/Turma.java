package br.com.summit.school.model;

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
    private Long id_turma;

    @Column(nullable = false, length = 50)
    private String nome_da_turma;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno")
    private Turno turno;

    @Column(name = "ano", nullable = false, length = 4)
    private String ano;

    @Enumerated(EnumType.STRING)
    @Column(name = "semestre")
    private Semestre semestre;


}
