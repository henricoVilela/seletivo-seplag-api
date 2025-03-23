package br.gov.br.seplag_api.api.model.security;

import jakarta.validation.constraints.NotBlank;

public class JwtResponse {
	
	@NotBlank(message = "não pode está em branco")
	private String token;
    
    public JwtResponse(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
}
