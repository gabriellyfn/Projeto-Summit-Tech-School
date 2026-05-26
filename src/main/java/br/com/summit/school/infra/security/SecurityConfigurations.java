package br.com.summit.school.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigurations {

  @Autowired
  private SecurityFilter securityFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http){
      return http.csrf(csrf -> csrf.disable())
              .sessionManagement( sm -> sm
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests(auth -> auth
              .requestMatchers("/login").permitAll()
              .anyRequest().authenticated())
              .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
              .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
      return configuration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder encoder(){
      return  new BCryptPasswordEncoder();
  }
}
