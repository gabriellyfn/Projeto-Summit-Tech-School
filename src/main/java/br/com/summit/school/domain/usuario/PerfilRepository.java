package br.com.summit.school.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository  extends JpaRepository<Perfil, Long> {

    //metodo simples que vai buscar o perfil pelo nome
    Optional<Perfil> findByNome(String nome);
}
