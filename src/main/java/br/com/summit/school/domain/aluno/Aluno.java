package br.com.summit.school.domain.aluno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Aluno")
@Table(name = "Aluno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String foto;

    @Column(name = "data_de_nascimento", nullable = false)
    private LocalDate dataDeNascimento;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 25)
    private String telefone;
}