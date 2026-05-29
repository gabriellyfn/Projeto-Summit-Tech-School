package br.com.summit.school.infra.security;

import lombok.Builder;

@Builder
public record DadosTokenJWT(String tokenJWT) {
}
