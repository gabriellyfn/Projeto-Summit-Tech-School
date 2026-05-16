package br.com.summit.school.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "ALUNO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Integer id;

    @Column(length = 255)
    private String nome;

    @Column(length = 255)
    private String foto;

    @Column(name = "data_nasc")
    private LocalDate dataNasc;

    @Column(length = 255)
    private String email;

    @Column(length = 25)
    private String telefone;

}
