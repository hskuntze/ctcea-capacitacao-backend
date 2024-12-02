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

import br.com.sad2.capacitacao.dto.AvaliacaoTreinamentoDTO;
import br.com.sad2.capacitacao.servicos.AvaliacaoTreinamentoServico;

@RestController
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoTreinamentoControlador {

	@Autowired
	private AvaliacaoTreinamentoServico avaliacaoTreinamentoServico;
	
	@GetMapping
	public ResponseEntity<List<AvaliacaoTreinamentoDTO>> buscarTodos() {
		return ResponseEntity.ok().body(avaliacaoTreinamentoServico.buscarTodos());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AvaliacaoTreinamentoDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(avaliacaoTreinamentoServico.buscarPorId(id));
	}
	
	@PostMapping(value = "/registrar")
	public ResponseEntity<AvaliacaoTreinamentoDTO> registrar(@RequestBody AvaliacaoTreinamentoDTO dto) {
		AvaliacaoTreinamentoDTO av = avaliacaoTreinamentoServico.registrar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(av.getId()).toUri();
		
		return ResponseEntity.created(uri).body(av);
	}
	
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<AvaliacaoTreinamentoDTO> atualizar(@PathVariable Long id, @RequestBody AvaliacaoTreinamentoDTO dto) {
		AvaliacaoTreinamentoDTO av = avaliacaoTreinamentoServico.atualizar(id, dto);
		return ResponseEntity.ok().body(av);
	}
	
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		avaliacaoTreinamentoServico.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
