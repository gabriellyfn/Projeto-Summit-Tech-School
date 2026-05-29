package br.com.summit.school.domain.usuario;

import br.com.summit.school.infra.exception.exceptionLogin.AutenticacaoException;
import br.com.summit.school.infra.exception.exceptionLogin.ErrorHandlerGerenciador;
import br.com.summit.school.infra.security.DadosTokenJWT;
import br.com.summit.school.infra.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final TokenService tokenService;
    private final AuthenticationManager manager;
    private final ErrorHandlerGerenciador errorHandlerGerenciador;

    public LoginService(TokenService tokenService, AuthenticationManager manager, ErrorHandlerGerenciador errorHandlerGerenciador) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.errorHandlerGerenciador = errorHandlerGerenciador;
    }

    public DadosTokenJWT logar(DadosAutenticacao dados) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    dados.login(),
                    dados.senha());
            Authentication authentication = manager.authenticate(token);

            String tokenJWT = tokenService
                    .gerarToken((Usuario) authentication.getPrincipal());
            return new DadosTokenJWT(tokenJWT);
        } catch (AuthenticationException e) {
            var handler = errorHandlerGerenciador.getHandler(e);
            if (handler != null) {
                throw  new AutenticacaoException(handler.mensagem(e));
            }
            throw new AutenticacaoException("Erro na autenticação" + e.getMessage(),e);
        }
    }
}



