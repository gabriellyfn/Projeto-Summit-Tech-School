package br.com.summit.school.domain.usuario;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final PasswordEncoder encoder;
    private final UsuarioRepository repository;
    private final PerfilRepository  perfilRepository;

    public UsuarioService(PasswordEncoder encoder, UsuarioRepository repository, PerfilRepository perfilRepository) {
        this.encoder = encoder;
        this.repository = repository;
        this.perfilRepository = perfilRepository;
    }

    @Transactional
    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuario dados) {

        Perfil perfil = perfilRepository.findByNome(dados.perfil().name())
                .orElseThrow(()-> new RuntimeException("Este Perfil não foi encontrado:"+ dados.perfil()));

        String senhaCriptografada = encoder.encode(dados.senha());
        
        Usuario usuario = new Usuario(
                null,
                dados.login(),
                "",
                dados.login(),
                senhaCriptografada,
                perfil,
                "",
                dados.email(),
                null
        );

        repository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
