package br.com.summit.school.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Usuario")
@Table(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String foto;

    @Column(nullable = false, unique = true, length = 155)
    private String login;

    @Column(nullable = false, length = 255)
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_perfil", nullable = false)
    private String perfil;

    @Column(length = 25)
    private String telefone;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "data_de_nascimento")
    private LocalDate dataNascimento;
}
