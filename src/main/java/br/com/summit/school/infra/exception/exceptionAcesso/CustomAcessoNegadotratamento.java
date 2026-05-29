package br.com.summit.school.infra.exception.exceptionAcesso;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

//CRIANDO O COMPONENTE QUE SERÁ UTILIZADO LÁ NA SecurityConfiguration para printar  o Desgin no estilo JSon
@Component
public class CustomAcessoNegadotratamento implements AccessDeniedHandler{
    private  final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                "Acesso Negado. Você não tem permissão para acessar este recurso.",
                request.getRequestURI(),
                request.getPathInfo());

        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
