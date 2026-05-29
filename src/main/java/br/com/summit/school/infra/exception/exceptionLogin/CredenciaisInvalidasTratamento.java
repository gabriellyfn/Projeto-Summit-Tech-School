package br.com.summit.school.infra.exception.exceptionLogin;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CredenciaisInvalidasTratamento implements TratamentoError {
    @Override
    public boolean aplicar(Throwable error) {
        return error instanceof BadCredentialsException || error  instanceof UsernameNotFoundException;
    }

    @Override
    public String mensagem(Throwable error) {
        return "Credenciais invalidas. verifique seu login ou senha";
    }

    @Override
    public int codigoHttpStatus(Throwable error) {
        return 401;
    }
}
