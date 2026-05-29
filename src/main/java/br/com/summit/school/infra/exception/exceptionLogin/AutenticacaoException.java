package br.com.summit.school.infra.exception.exceptionLogin;

public class AutenticacaoException extends RuntimeException {

        public AutenticacaoException(String mensagem){
         super(mensagem);
       }

       public AutenticacaoException(String message, Throwable cause){
            super(message,cause);
       }
       public class CredenciaisInvalidasException extends AutenticacaoException{
            public CredenciaisInvalidasException(){
                super("Login ou Senha Invalida");
            }
       }
       public class TokenInvalidoException extends AutenticacaoException{
            public TokenInvalidoException(){
                super("Token invalido");
            }
       }
}
