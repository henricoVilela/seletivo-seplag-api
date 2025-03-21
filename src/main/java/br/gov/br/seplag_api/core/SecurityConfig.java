package br.gov.br.seplag_api.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/api/public/**").permitAll();
                authorize.requestMatchers("/api/**").hasRole("ADMIN");
                authorize.anyRequest().authenticated();
            })
            .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
    
    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("usuario")
                .password(passwordEncoder().encode("senha_teste"))
                .roles("USER")
                .build();
        
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("senha_admin"))
                .roles("ADMIN")
                .build();
        
        return new InMemoryUserDetailsManager(user, admin);
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}