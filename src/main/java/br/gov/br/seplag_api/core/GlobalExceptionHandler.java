package br.gov.br.seplag_api.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.gov.br.seplag_api.domain.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
	public final ResponseEntity<Object> handleMissingServletRequestParameterException(final MissingServletRequestParameterException ex, final HttpServletRequest request) {
		Map<String, String> error = new HashMap<>();
	    error.put("message", "Requisição mal formada");
	    error.put("details", String.format("O parâmetro '%s' não pode ser nulo", ex.getParameterName()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
    
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return error;
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "Ocorreu um erro interno no servidor");
        error.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    

}