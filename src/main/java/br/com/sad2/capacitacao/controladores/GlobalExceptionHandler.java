package br.com.sad2.capacitacao.controladores;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sad2.capacitacao.servicos.excecoes.RecursoExistenteException;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private Environment env;

	private static String FRONT_APP_URL = "";

	/**
	 * Endpoint que captura e trata as exceções do tipo RecursoNaoEncontradoException. Quando uma exceção tem "Token" na mensagem
	 * realiza um redirecionamento para a página "nao-encontrado" do front-end. Este tratamento é específico para os cliques realizados
	 * nos e-mails já que não partem do front-end.
	 * <p>
	 * Caso não exista "Token" a função retorna um objeto JSON que contém as propriedades "error" e "message" para que o front-end possa
	 * realizar os devidos tratamentos.
	 * @return JSON
	 */
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<Map<String, String>> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")
				|| Arrays.asList(env.getActiveProfiles()).contains("dev")) {
			FRONT_APP_URL = "http://localhost:3000";
		}

		if (ex.getMessage().contains("Token")) {
	        String frontEndUrl = FRONT_APP_URL + "/sgc/nao-encontrado?message=" + URLEncoder.encode(ex.getMessage(), StandardCharsets.UTF_8);
	        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(frontEndUrl)).build();
	    } else {
	        Map<String, String> response = new HashMap<>();
	        response.put("error", "Recurso não encontrado");
	        response.put("message", ex.getMessage());

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}
	
	/**
	 * Endpoint que captura e trata as exceções do tipo RecursoExistenteException, e que retorna um objeto JSON que contém as propriedades 
	 * "error" e "message" para que o front-end possa realizar os devidos tratamentos.
	 * @return JSON
	 */
	@ExceptionHandler(RecursoExistenteException.class)
	public ResponseEntity<Map<String, String>> handleRecursoExistenteException(RecursoExistenteException ex) {
		Map<String, String> response = new HashMap<>();
        response.put("error", "Recurso já existente");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}
}
