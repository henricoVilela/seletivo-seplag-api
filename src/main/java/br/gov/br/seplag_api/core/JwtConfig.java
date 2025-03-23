package br.gov.br.seplag_api.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
	
	@Value("${jwt.secret:default_secret_key_for_development}")
    private String secret;
    
    @Value("${jwt.expiration:300}") // 5 minutos em segundos
    private long expiration;
    
    public String getSecret() {
        return secret;
    }
    
    public long getExpiration() {
        return expiration;
    }
}
