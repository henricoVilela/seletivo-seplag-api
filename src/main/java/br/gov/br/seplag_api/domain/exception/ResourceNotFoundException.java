package br.gov.br.seplag_api.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s não encontrado com %s: '%s'", resourceName, fieldName, fieldValue));
    }
}