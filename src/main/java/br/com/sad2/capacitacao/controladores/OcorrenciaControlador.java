package br.com.sad2.capacitacao.controladores;

import java.net.URI;
import java.util.List;

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

import br.com.sad2.capacitacao.dto.OcorrenciaDTO;
import br.com.sad2.capacitacao.servicos.OcorrenciaServico;

@RestController
@RequestMapping(value = "/ocorrencias")
public class OcorrenciaControlador {

	@Autowired
	private OcorrenciaServico ocorrenciaServico;
	
	/**
	 * --------- GETS ---------
	 */
	
	/**
	 * Endpoint para buscar todos as ocorrências
	 */
	@GetMapping
	public ResponseEntity<List<OcorrenciaDTO>> buscarTodos() {
		return ResponseEntity.ok().body(ocorrenciaServico.buscarTodos());
	}
	
	/**
	 * Endpoint para buscar ocorrência por ID
	 * @param id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<OcorrenciaDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(ocorrenciaServico.buscarPorId(id));
	}
	
	/**
	 * --------- POSTS ---------
	 */
	
	/**
	 * Endpoint para registrar uma ocorrência
	 * @param dto
	 */
	@PostMapping(value = "/registrar")
	public ResponseEntity<OcorrenciaDTO> registrar(@RequestBody OcorrenciaDTO dto) {
		OcorrenciaDTO element = ocorrenciaServico.registrar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(element);
	}
	
	/**
	 * --------- PUT ---------
	 */
	
	/**
	 * Endpoint para atualizar uma ocorrência baseada no ID
	 * @param id
	 * @param dto
	 */
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<OcorrenciaDTO> atualizar(@PathVariable Long id, @RequestBody OcorrenciaDTO dto) {
		OcorrenciaDTO element = ocorrenciaServico.atualizar(id, dto);
		return ResponseEntity.ok().body(element);
	}
	
	/**
	 * --------- DELETE ---------
	 */
	
	/**
	 * Endpoint para excluir uma ocorrência baseada no ID
	 * @param id
	 */
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		ocorrenciaServico.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
