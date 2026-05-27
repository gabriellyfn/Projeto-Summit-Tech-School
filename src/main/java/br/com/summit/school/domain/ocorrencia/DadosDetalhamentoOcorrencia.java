package br.com.summit.school.domain.ocorrencia;

import br.com.summit.school.model.Tipo_Ocorrencia;
import java.time.LocalDate;
import java.time.LocalTime;

public record DadosDetalhamentoOcorrencia(
        Long id,

        String aluno,
        String turma,
        String usuario,
        String tipoOcorrencia,

        Tipo_Ocorrencia categoriaOcorrencia,

        LocalDate data,
        LocalTime hora,

        String descricao

) {

    public DadosDetalhamentoOcorrencia(Ocorrencia ocorrencia) {
        this(
                ocorrencia.getId_ocorrencia(),
                ocorrencia.getAluno().getNome(),
                ocorrencia.getTurma().getNomeDaTurma(),
                ocorrencia.getUsuario().getNome(),
                ocorrencia.getTipo_ocorrencia().getNome_Ocorrencia().toString(),
                ocorrencia.getCategoria_ocorrencia(),
                ocorrencia.getData(),
                ocorrencia.getHora(),
                ocorrencia.getDescricao()
        );
    }
}
