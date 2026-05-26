package br.com.summit.school.domain.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Perfil")
@Table(name = "Perfil")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id_perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_perfil;

    @Column(name = "nome_perfil", nullable = false, unique = true )
    private String nome;
}