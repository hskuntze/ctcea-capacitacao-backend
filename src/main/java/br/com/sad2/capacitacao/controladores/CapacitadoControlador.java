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

import br.com.sad2.capacitacao.dto.CapacitadoDTO;
import br.com.sad2.capacitacao.dto.CapacitadoRegistroDTO;
import br.com.sad2.capacitacao.servicos.CapacitadoServico;

@RestController
@RequestMapping(value = "/capacitados")
public class CapacitadoControlador {

	@Autowired
	private CapacitadoServico capacitadoServico;

	/**
	 * --------- GETS ---------
	 */
	@GetMapping
	public ResponseEntity<List<CapacitadoDTO>> buscarTodos() {
		return ResponseEntity.ok().body(capacitadoServico.buscarTodos());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CapacitadoDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(capacitadoServico.buscarPorId(id));
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<CapacitadoDTO> buscarPorEmail(@PathVariable String email) {
		return ResponseEntity.ok().body(capacitadoServico.buscarPorEmail(email));
	}

	/**
	 * --------- POSTS ---------
	 */
	@PostMapping
	public ResponseEntity<CapacitadoDTO> registrar(@RequestBody CapacitadoRegistroDTO dto) {
		CapacitadoDTO elemento = capacitadoServico.registrar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(elemento.getId()).toUri();

		return ResponseEntity.created(uri).body(elemento);
	}

	/**
	 * --------- PUT ---------
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<CapacitadoDTO> atualizar(@PathVariable Long id, @RequestBody CapacitadoRegistroDTO dto) {
		CapacitadoDTO atualizado = capacitadoServico.atualizar(id, dto);
		return ResponseEntity.ok().body(atualizado);
	}

	/**
	 * --------- DELETE ---------
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		capacitadoServico.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
