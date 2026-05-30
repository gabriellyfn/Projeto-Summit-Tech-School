package br.com.summit.school.infra.security;

import br.com.summit.school.infra.exception.exceptionAcesso.AuthenticationEndPoint;
import br.com.summit.school.infra.exception.exceptionAcesso.CustomAcessoNegadotratamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigurations {

  @Autowired
  private SecurityFilter securityFilter;
  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private CustomAcessoNegadotratamento customAcessoNegadotramento;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http
              .cors(Customizer.withDefaults()) // Habilita a configuração de CORS
              .csrf(csrf -> csrf.disable())
              .sessionManagement( sm -> sm
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests(auth -> auth
              .requestMatchers(HttpMethod.POST,"/login").permitAll()
              .anyRequest().authenticated())
              .exceptionHandling(exceptions -> exceptions
                      .authenticationEntryPoint(authenticationEntryPoint)
                      .accessDeniedHandler(customAcessoNegadotramento))
              .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
              .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
      return configuration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder encoder(){
      return  new BCryptPasswordEncoder();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    // Permite requisições da origem do seu front-end Angular
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
    // Permite os métodos HTTP mais comuns
    configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
    // Permite cabeçalhos comuns, incluindo o de Autorização
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
