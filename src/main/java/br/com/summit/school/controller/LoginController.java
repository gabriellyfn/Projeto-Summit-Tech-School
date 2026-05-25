package br.com.summit.school.controller;

import br.com.summit.school.domain.usuario.DadosAutenticacao;
import br.com.summit.school.domain.usuario.LoginService;
import br.com.summit.school.infra.security.DadosTokenJWT;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService service;

    public LoginController(LoginService service ){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DadosTokenJWT>EfetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        DadosTokenJWT dto = service.logar(dados);
        return ResponseEntity.ok(dto);
    }
}
