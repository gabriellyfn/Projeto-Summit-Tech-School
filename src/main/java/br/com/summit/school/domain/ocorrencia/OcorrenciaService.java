package br.com.summit.school.domain.ocorrencia;

import br.com.summit.school.model.Tipo_Ocorrencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.summit.school.domain.aluno.AlunoRepository;
import br.com.summit.school.domain.turma.AlunoTurmaRepository;
import br.com.summit.school.domain.turma.TurmaRepository;
import br.com.summit.school.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

    private final AlunoRepository alunoRepository;

    private final TurmaRepository turmaRepository;

    private final UsuarioRepository usuarioRepository;

    private final TipoOcorrenciaRepository tipoOcorrenciaRepository;

    private final AlunoTurmaRepository alunoTurmaRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository, AlunoRepository alunoRepository, TurmaRepository turmaRepository, UsuarioRepository usuarioRepository, TipoOcorrenciaRepository tipoOcorrenciaRepository, AlunoTurmaRepository alunoTurmaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.usuarioRepository = usuarioRepository;
        this.tipoOcorrenciaRepository = tipoOcorrenciaRepository;
        this.alunoTurmaRepository = alunoTurmaRepository;
    }

    @Transactional
    public DadosDetalhamentoOcorrencia cadastrar(DadosCadastroOcorrencia dados, Long idUsuarioLogado) {

        var aluno = alunoRepository.findById(dados.idAluno())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + dados.idAluno()));

        var turma = turmaRepository.findById(dados.idTurma())
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com o ID: " + dados.idTurma()));

        var usuario = usuarioRepository.findById(idUsuarioLogado)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + idUsuarioLogado));

        var tipoOcorrencia = tipoOcorrenciaRepository.findById(dados.idTipoOcorrencia())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Ocorrência não encontrado com o ID: " + dados.idTipoOcorrencia()));

        if (!alunoTurmaRepository.existsByTurmaIdTurmaAndAlunoId(dados.idTurma(), dados.idAluno())) {
            throw new IllegalArgumentException("Operação inválida: O aluno de ID " + dados.idAluno() + " não está matriculado na turma de ID " + dados.idTurma() + ".");
        }

        var ocorrencia = new Ocorrencia();
        ocorrencia.setAluno(aluno);
        ocorrencia.setTurma(turma);
        ocorrencia.setUsuario(usuario);
        ocorrencia.setTipo_ocorrencia(tipoOcorrencia);
        ocorrencia.setData(dados.data());
        ocorrencia.setHora(dados.hora());
        
        ocorrencia.setCategoria_ocorrencia(Tipo_Ocorrencia.valueOf(dados.categoriaOcorrencia().name()));
        ocorrencia.setDescricao(dados.descricao());

        ocorrenciaRepository.save(ocorrencia);

        return new DadosDetalhamentoOcorrencia(ocorrencia);
    }

    public Page<DadosListagemOcorrencia> listar(Long idAluno, Long idTurma, LocalDate dataInicio, LocalDate dataFim, Long idCategoria, Pageable paginacao) {
        Specification<Ocorrencia> spec = OcorrenciaSpecification.comFiltros(idAluno, idTurma, dataInicio, dataFim, idCategoria);
        return ocorrenciaRepository.findAll(spec, paginacao).map(DadosListagemOcorrencia::new);
    }

    public DadosDetalhamentoOcorrencia detalhar(Long id) {
        var ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ocorrência não encontrada com o ID: " + id));
        
        return new DadosDetalhamentoOcorrencia(ocorrencia);
    }

    public Page<DadosListagemOcorrencia> listarHistoricoPorAluno (Long idAluno, Pageable paginacao) {
        if (!alunoRepository.existsById(idAluno)) {
            throw new jakarta.persistence.EntityNotFoundException("Aluno não encontrado com o ID: " + idAluno);
        }

        return ocorrenciaRepository.findByAlunoId(idAluno, paginacao).map(DadosListagemOcorrencia::new);
    }

}
