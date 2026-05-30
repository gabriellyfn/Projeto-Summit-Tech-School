package br.com.summit.school.domain.ocorrencia;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OcorrenciaSpecification {

    public static Specification<Ocorrencia> comFiltros(Long idAluno, Long idTurma, LocalDate dataInicio, LocalDate dataFim, Long idCategoria) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (idAluno != null) {
                predicates.add(criteriaBuilder.equal(root.get("aluno").get("id_aluno"), idAluno));
            }

            if (idTurma != null) {
                predicates.add(criteriaBuilder.equal(root.get("turma").get("id_turma"), idTurma));
            }

            if (dataInicio != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("data"), dataInicio));
            }

            if (dataFim != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("data"), dataFim));
            }

            if (idCategoria != null) {
                predicates.add(criteriaBuilder.equal(root.get("tipo_ocorrencia").get("id_tipo_ocorrencia"), idCategoria));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}