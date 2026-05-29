package br.com.summit.school.infra.exception.exceptionAcesso;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

//CRIANDO O COMPONENTE QUE SERÁ UTILIZADO LÁ NA SecurityConfiguration para printar  o Desgin no estilo JSon
@Component
public class AuthenticationEndPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                "Credenciais invalidas. Verifique os campos Login e Senha.",
                request.getRequestURI());

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
