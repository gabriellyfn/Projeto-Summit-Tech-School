package br.com.summit.school.domain.turma;

public record DadosListagemTurma(
        Long id,
        String nome,
        String turno,
        String semestre
) {
    public DadosListagemTurma(Turma turma) {
        this(turma.getIdTurma(), turma.getNomeDaTurma(), turma.getTurno().name(), turma.getSemestre().name());
    }
}
