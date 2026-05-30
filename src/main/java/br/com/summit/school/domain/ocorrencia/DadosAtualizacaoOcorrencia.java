package br.com.summit.school.domain.ocorrencia;

import br.com.summit.school.model.Tipo_Ocorrencia;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record DadosAtualizacaoOcorrencia(

        Long idAluno,

        Long idTurma,

        Long idTipoOcorrencia,

        Tipo_Ocorrencia categoriaOcorrencia,

        LocalDate data,

        LocalTime hora,

        @Size(max = 255)
        String descricao
){
}