package br.com.summit.school.domain.turma;

import br.com.summit.school.domain.aluno.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoTurmaRepository alunoTurmaRepository;

    @Transactional
    public void vincularAlunos(Long turmaId, DadosVinculoAlunoTurma dados) {
        var turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com o ID: " + turmaId));

        for (Long alunoId : dados.alunosIds()) {
            var aluno = alunoRepository.findById(alunoId)
                    .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + alunoId));

            if (alunoTurmaRepository.existsByTurmaIdTurmaAndAlunoId(turmaId, alunoId)) {
                throw new IllegalArgumentException("O aluno de ID " + alunoId + " já está matriculado nesta turma.");
            }

        var vinculo = new AlunoTurma();
        vinculo.setTurma(turma);
        vinculo.setAluno(aluno);

        alunoTurmaRepository.save(vinculo);
        }
    }
}
