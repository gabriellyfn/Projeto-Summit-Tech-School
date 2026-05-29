package br.com.summit.school.infra.exception.exceptionLogin;

//interface para ser utilizada junto com um tratador global de exeception
public interface TratamentoError {
    boolean aplicar(Throwable error);
    String mensagem(Throwable error);
    int codigoHttpStatus(Throwable error);
}
