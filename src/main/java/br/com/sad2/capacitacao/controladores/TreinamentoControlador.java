package br.com.sad2.capacitacao.controladores;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sad2.capacitacao.dto.TreinamentoDTO;
import br.com.sad2.capacitacao.servicos.TreinamentoServico;

@RestController
@RequestMapping(value = "/treinamentos")
public class TreinamentoControlador {

	@Autowired
	private TreinamentoServico treinamentoServico;

	/**
	 * --------- GETS ---------
	 */
	@GetMapping
	public ResponseEntity<List<TreinamentoDTO>> buscarTodos() {
		return ResponseEntity.ok().body(treinamentoServico.buscarTodos());
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<TreinamentoDTO>> buscarTodosComUsuario() {
		return ResponseEntity.ok().body(treinamentoServico.buscarTodosComUsuario());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TreinamentoDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(treinamentoServico.buscarPorId(id));
	}

	@GetMapping(value = "/{id}/users")
	public ResponseEntity<TreinamentoDTO> buscarPorIdComUsuarios(@PathVariable Long id) {
		return ResponseEntity.ok().body(treinamentoServico.buscarPorIdComUsuarios(id));
	}
	
	/**
	 * --------- POSTS ---------
	 */
	@PostMapping(value = "/registrar")
	public ResponseEntity<TreinamentoDTO> registrar(@RequestBody TreinamentoDTO dto) {
		TreinamentoDTO treinamento = treinamentoServico.registrar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(treinamento);
	}
	
	/**
	 * --------- PUTS ---------
	 */
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<TreinamentoDTO> atualizar(@PathVariable Long id, @RequestBody TreinamentoDTO dto) {
		TreinamentoDTO treinamento = treinamentoServico.atualizar(id, dto);
		return ResponseEntity.ok().body(treinamento);
	}
	
	@PutMapping(value = "/inserir/user")
	public ResponseEntity<TreinamentoDTO> inserirUsuarioEmTreinamento(@RequestBody Map<String, Object> requestBody) {
	    Long idUsuario = Long.valueOf(requestBody.get("idUsuario").toString());
	    Long idTreinamento = Long.valueOf(requestBody.get("idTreinamento").toString());
		
	    return ResponseEntity.ok().body(treinamentoServico.inserirUsuarioEmTreinamento(idTreinamento, idUsuario));
	}
	
	@PutMapping(value = "/remover/user")
	public ResponseEntity<TreinamentoDTO> removerUsuarioEmTreinamento(@RequestBody Map<String, Object> requestBody) {
	    Long idUsuario = Long.valueOf(requestBody.get("idUsuario").toString());
	    Long idTreinamento = Long.valueOf(requestBody.get("idTreinamento").toString());
		
	    return ResponseEntity.ok().body(treinamentoServico.removerUsuarioEmTreinamento(idTreinamento, idUsuario));
	}
	
	/**
	 * --------- DELETE ---------
	 */
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		treinamentoServico.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
