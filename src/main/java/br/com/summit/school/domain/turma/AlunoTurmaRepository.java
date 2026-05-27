package br.com.summit.school.domain.turma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoTurmaRepository extends JpaRepository<AlunoTurma, Long> {

    boolean existsByTurmaIdTurmaAndAlunoId(Long idTurma, Long idAluno);

}