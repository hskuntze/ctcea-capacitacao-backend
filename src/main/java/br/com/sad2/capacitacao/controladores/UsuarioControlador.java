package br.com.sad2.capacitacao.controladores;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sad2.capacitacao.controladores.eventos.OnRegistrationCompleteEvent;
import br.com.sad2.capacitacao.dto.TokenSenhaDTO;
import br.com.sad2.capacitacao.dto.UsuarioDTO;
import br.com.sad2.capacitacao.dto.UsuarioRegistroDTO;
import br.com.sad2.capacitacao.servicos.TokenServico;
import br.com.sad2.capacitacao.servicos.UsuarioServico;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioControlador {

	@Autowired
	private UsuarioServico usuarioServico;
	
	@Autowired
	private TokenServico tokenServico;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private Environment env;

	private static String FRONT_APP_URL = "http://localhost:3056";
	
	/**
	 * --------- GETS ---------
	 */
	/**
	 * Endpoint para resgatar todos os usuários
	 * @param id
	 * @return UsuarioDTO
	 */
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
		return ResponseEntity.ok().body(usuarioServico.buscarTodos());
	}
	
	/**
	 * Endpoint para resgatar um usuário baseado em seu identificador único
	 * @param id
	 * @return UsuarioDTO
	 */
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(usuarioServico.buscarPorId(id));
	}
	
	/**
	 * Endpoint para resgatar um usuário baseado em seu email
	 * @param email
	 * @return UsuarioDTO
	 */
	@GetMapping(value = "/email")
	public ResponseEntity<UsuarioDTO> buscarPorEmail(@RequestBody String email) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(email);
			String emailExtraido = jsonNode.get("email").asText();
			
			return ResponseEntity.ok().body(usuarioServico.buscarPorEmail(emailExtraido));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	/**
	 * Endpoint para resgatar um usuário baseado em seu email
	 * @param email
	 * @return UsuarioDTO
	 */
	@GetMapping(value = "/identidade")
	public ResponseEntity<UsuarioDTO> buscarPorIdentidade(@RequestBody String identidade) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(identidade);
			String emailExtraido = jsonNode.get("identidade").asText();
			
			return ResponseEntity.ok().body(usuarioServico.buscarPorIdentidade(emailExtraido));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	/**
	 * Endpoint para confirmar o registro do usuário
	 * @param request
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/confirmar")
	public ResponseEntity<Void> confirmar(WebRequest request, @RequestParam("token") String token) {
		usuarioServico.habilitarUsuarioRegistrado(token);
		
		if (Arrays.asList(env.getActiveProfiles()).contains("test")
				|| Arrays.asList(env.getActiveProfiles()).contains("dev")) {
			FRONT_APP_URL = "http://localhost:3000";
		}

		return ResponseEntity.status(HttpStatus.FOUND)
				.location(URI.create(FRONT_APP_URL + "/sgc/confirmado")).build();
	}
	
	/**
	 * Endpoint que é chamado para redirecionar o usuário para o front-end
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/redirecionarParaTrocarSenha")
	public ResponseEntity<Void> redirecionarTrocaDeSenha(@RequestParam("token") String token) {
		tokenServico.validarTokenRecuperacao(token);
		
		if (Arrays.asList(env.getActiveProfiles()).contains("test")
				|| Arrays.asList(env.getActiveProfiles()).contains("dev")) {
			FRONT_APP_URL = "http://localhost:3000";
		}

		return ResponseEntity.status(HttpStatus.FOUND)
				.location(URI.create(FRONT_APP_URL + "/sgc/recuperarsenha/" + token)).build();
	}
	
	/**
	 * --------- POST ---------
	 */
	
	/**
	 * Endpoint para registrar um usuário na aplicação
	 * @param dto
	 * @param request
	 * @param errors
	 * @return UsuarioDTO
	 */
	@PostMapping(value = "/registrar")
	public ResponseEntity<UsuarioDTO> registrar(@RequestBody UsuarioRegistroDTO dto, HttpServletRequest request, Errors errors) {
		UsuarioDTO usuario = usuarioServico.registrar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
//		String appUrl = getAppUrl(request);
//		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(usuario, request.getLocale(), appUrl));
		
		return ResponseEntity.created(uri).body(usuario);
	}
	
	/**
	 * Endpoint que chama a função de envio de e-mail do token de recuperação/troca de senha
	 * @param email
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/recuperar")
	public ResponseEntity<String> enviarEmailDeRecuperacao(@RequestParam("email") String email, HttpServletRequest request) {
		String appUrl = getAppUrl(request);
		tokenServico.enviarToken(email, appUrl);
		
		return ResponseEntity.ok().body("Enviado");
	}
	
	/**
	 * Endpoint que invoca as funções para realizar a troca da senha do usuário
	 * @param dto
	 * @return
	 */
	@PostMapping(value = "/salvarTrocaDeSenha")
	public ResponseEntity<Void> salvarTrocaDeSenha(@RequestParam String novaSenha, @RequestParam String senhaAntiga) {
		usuarioServico.trocarSenhaDoUsuario(novaSenha, senhaAntiga);
		return ResponseEntity.ok().build();
	}
//	public ResponseEntity<Void> salvarTrocaDeSenha(@RequestBody TokenSenhaDTO dto) {
//		tokenServico.validarTokenRecuperacao(dto.getToken());
//		usuarioServico.trocarSenhaDoUsuarioComToken(dto);
//		
//		return ResponseEntity.ok().build();
//	}
	
	/**
	 * --------- PUT ---------
	 */
	
	/**
	 * Endpoint que atualiza o usuário
	 * @param id
	 * @param dto
	 * @return
	 */
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
		UsuarioDTO atualizado = usuarioServico.atualizar(id, dto);
		return ResponseEntity.ok().body(atualizado);
	}
	
	/**
	 * --------- DELETE ---------
	 */
	
	/**
	 * Endpoint que deleta o usuário (baseado no ID)
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		usuarioServico.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	private String getAppUrl(HttpServletRequest request) {
		String aux = request.getRequestURL().toString();
		return aux.substring(0, StringUtils.ordinalIndexOf(aux, "/", 3));
	}
}
