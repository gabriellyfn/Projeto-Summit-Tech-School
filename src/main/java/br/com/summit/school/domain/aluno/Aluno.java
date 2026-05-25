package br.com.summit.school.domain.aluno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "O nome é obrigatório e não pode estar em branco")
    @Column(nullable = false, length = 255)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String foto;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Column(name = "data_de_nascimento", nullable = false)
    private LocalDate dataDeNascimento;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O formato do email é inválido")
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotBlank(message= "O telefone é obrigatório")
    @Column(nullable = false, length = 25)
    private String telefone;
}