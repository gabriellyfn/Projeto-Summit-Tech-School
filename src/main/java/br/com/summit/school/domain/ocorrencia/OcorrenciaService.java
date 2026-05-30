package br.com.summit.school.domain.ocorrencia;

import br.com.summit.school.domain.usuario.Usuario;
import br.com.summit.school.model.Tipo_Ocorrencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import br.com.summit.school.domain.aluno.AlunoRepository;
import br.com.summit.school.domain.turma.AlunoTurmaRepository;
import br.com.summit.school.domain.turma.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

    private final AlunoRepository alunoRepository;

    private final TurmaRepository turmaRepository;

    private final TipoOcorrenciaRepository tipoOcorrenciaRepository;

    private final AlunoTurmaRepository alunoTurmaRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository, AlunoRepository alunoRepository, TurmaRepository turmaRepository, TipoOcorrenciaRepository tipoOcorrenciaRepository, AlunoTurmaRepository alunoTurmaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.tipoOcorrenciaRepository = tipoOcorrenciaRepository;
        this.alunoTurmaRepository = alunoTurmaRepository;
    }

    @Transactional
    public DadosDetalhamentoOcorrencia cadastrar(
            DadosCadastroOcorrencia dados) {

        var aluno = alunoRepository.findById(dados.idAluno())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + dados.idAluno()));

        var turma = turmaRepository.findById(dados.idTurma())
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com o ID: " + dados.idTurma()));

        var usuario = usuarioLogado();

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

    @Transactional
    public DadosDetalhamentoOcorrencia atualizar(
            Long id,
            DadosAtualizacaoOcorrencia dados
    ) {

        var ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Ocorrência não encontrada com o ID: " + id));

        validarDonoDaOcorrencia(ocorrencia);

        if (dados.idTipoOcorrencia() != null) {

            var tipoOcorrencia = tipoOcorrenciaRepository
                    .findById(dados.idTipoOcorrencia())
                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Tipo de Ocorrência não encontrado com o ID: "
                                            + dados.idTipoOcorrencia()));

            ocorrencia.setTipo_ocorrencia(tipoOcorrencia);
        }

        if (dados.categoriaOcorrencia() != null) {
            ocorrencia.setCategoria_ocorrencia(
                    Tipo_Ocorrencia.valueOf(
                            dados.categoriaOcorrencia().name()
                    )
            );
        }

        if (dados.descricao() != null) {
            ocorrencia.setDescricao(dados.descricao());
        }

        return new DadosDetalhamentoOcorrencia(ocorrencia);
    }

    public Page<DadosListagemOcorrencia> listarHistoricoPorAluno (Long idAluno, Pageable paginacao) {
        if (!alunoRepository.existsById(idAluno)) {
            throw new jakarta.persistence.EntityNotFoundException("Aluno não encontrado com o ID: " + idAluno);
        }

        return ocorrenciaRepository.findByAlunoId(idAluno, paginacao).map(DadosListagemOcorrencia::new);
    }

    @Transactional
    public void excluir(Long id) {

        var ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Ocorrência não encontrada com o ID: " + id));

        validarDonoDaOcorrencia(ocorrencia);

        ocorrenciaRepository.delete(ocorrencia);
    }
    private void validarDonoDaOcorrencia(Ocorrencia ocorrencia) {

        Usuario usuario = usuarioLogado();

        boolean admin = usuario.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (admin) {
            return;
        }

        if (!ocorrencia.getUsuario()
                .getId_usuario()
                .equals(usuario.getId_usuario())) {

            throw new IllegalArgumentException(
                    "Você só pode alterar ou excluir suas próprias ocorrências."
            );
        }
    }

    private Usuario usuarioLogado() {

        return (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
}

