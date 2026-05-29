package br.com.summit.school.infra.exception.exceptionLogin;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBloqueadoTratamento implements TratamentoError {
    @Override
    public boolean aplicar(Throwable error) {
        return error instanceof DisabledException || error instanceof LockedException;
    }

    @Override
    public String mensagem(Throwable error) {
        return "Conta Desativada. Entre em contato com o administrador.";
    }

    @Override
    public int codigoHttpStatus(Throwable error) {
        return 403;// erro Forbiden
    }
}
