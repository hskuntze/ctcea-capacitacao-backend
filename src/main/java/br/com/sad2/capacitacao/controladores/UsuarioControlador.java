package br.com.sad2.capacitacao.controladores;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sad2.capacitacao.controladores.eventos.OnRegistrationCompleteEvent;
import br.com.sad2.capacitacao.dto.UsuarioDTO;
import br.com.sad2.capacitacao.dto.UsuarioRegistroDTO;
import br.com.sad2.capacitacao.servicos.UsuarioServico;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioControlador {

	@Autowired
	private UsuarioServico usuarioServico;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	/**
	 * --------- GETS ---------
	 */
	
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
	 * Endpoint para confirmar o registro do usuário
	 * @param request
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/confirmar")
	public ResponseEntity<Void> confirmar(WebRequest request, @RequestParam("token") String token) {
		usuarioServico.habilitarUsuarioRegistrado(token);
		
		return ResponseEntity.ok().build();
	}
	
	private String getAppUrl(HttpServletRequest request) {
		String aux = request.getRequestURL().toString();
		return aux.substring(0, StringUtils.ordinalIndexOf(aux, "/", 3));
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
		
		String appUrl = getAppUrl(request);
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(usuario, request.getLocale(), appUrl));
		
		return ResponseEntity.created(uri).body(usuario);
	}
}
