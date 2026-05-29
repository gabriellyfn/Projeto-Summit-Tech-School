package br.com.summit.school.infra.exception.exceptionLogin;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ErrorHandlerGerenciador {

    private  final List<TratamentoError> tratamentos;

    public ErrorHandlerGerenciador(List<TratamentoError> tratamentos){
        this.tratamentos = tratamentos;
    }

    public TratamentoError getHandler(Throwable error){
        return tratamentos.stream()
                .filter(handlers -> handlers.aplicar(error))
                .findFirst()
                .orElse(null);
    }
}
