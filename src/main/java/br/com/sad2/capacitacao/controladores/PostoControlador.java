package br.com.sad2.capacitacao.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sad2.capacitacao.dto.PostoDTO;
import br.com.sad2.capacitacao.servicos.PostoServico;

@RestController
@RequestMapping(value = "/postos")
public class PostoControlador {

	@Autowired
	private PostoServico postoServico;
	
	@GetMapping
	public ResponseEntity<List<PostoDTO>> buscarTodos() {
		return ResponseEntity.ok().body(postoServico.buscarTodos());
	}
}
