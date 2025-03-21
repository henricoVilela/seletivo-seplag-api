package br.gov.br.seplag_api.commos;

import java.security.SecureRandom;

public class MatriculaUtils {
	
	public static String gerarMatricula() {
        String prefixo = "MAT";
        String sufixo = gerarSufixoAleatorio(4);

        return prefixo + sufixo;
    }
	
	private static String gerarSufixoAleatorio(int tamanho) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sufixo = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            int indice = random.nextInt(caracteres.length());
            sufixo.append(caracteres.charAt(indice));
        }
        return sufixo.toString();
    }

}
