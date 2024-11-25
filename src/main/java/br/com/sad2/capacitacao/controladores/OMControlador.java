package br.com.sad2.capacitacao.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sad2.capacitacao.dto.OMDTO;
import br.com.sad2.capacitacao.servicos.OMServico;

@RestController
@RequestMapping(value = "/oms")
public class OMControlador {

	@Autowired
	private OMServico omServico;
	
	@GetMapping
	public ResponseEntity<List<OMDTO>> buscarTodos() {
		return ResponseEntity.ok().body(omServico.buscarTodos());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OMDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(omServico.buscarPorId(id));
	}
	
	@GetMapping(value = "/bdas")
	public ResponseEntity<List<String>> buscarBDAsDistintos() {
		return ResponseEntity.ok().body(omServico.buscarBDAsDistintos());
	}
}
