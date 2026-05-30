package br.com.summit.school.domain.ocorrencia;

import br.com.summit.school.domain.dashboard.DadosRankingAluno;
import br.com.summit.school.domain.dashboard.DadosTotalCategoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>, JpaSpecificationExecutor<Ocorrencia> {

    @Query("SELECT new br.com.summit.school.domain.dashboard.DadosRankingAluno(o.aluno.id, o.aluno.nome, COUNT(o.id_ocorrencia)) " +
            "FROM Ocorrencia o " +
            "GROUP BY o.aluno.id, o.aluno.nome " +
            "ORDER BY COUNT(o.id_ocorrencia) DESC")
    List<DadosRankingAluno> findRankingAlunosReincidentes();

    // 2. Checklist: Query para "Total de ocorrências por categoria"
    @Query("SELECT new br.com.summit.school.domain.dashboard.DadosTotalCategoria(CAST(o.categoria_ocorrencia AS string), COUNT(o.id_ocorrencia)) " +
            "FROM Ocorrencia o " +
            "WHERE o.data BETWEEN :inicio AND :fim " +
            "GROUP BY o.categoria_ocorrencia")
    List<DadosTotalCategoria> findTotalPorCategoriaNoPeriodo(LocalDate inicio, LocalDate fim);

    Page<Ocorrencia> findByAlunoId(Long idAluno, Pageable paginacao);
}
