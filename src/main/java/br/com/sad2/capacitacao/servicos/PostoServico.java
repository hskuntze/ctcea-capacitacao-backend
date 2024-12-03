package br.com.sad2.capacitacao.servicos;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.PostoDTO;
import br.com.sad2.capacitacao.entities.Posto;
import br.com.sad2.capacitacao.repositorios.PostoRepositorio;

@Service
public class PostoServico {

	@Autowired
	private PostoRepositorio postoRepositorio;
	
	@Transactional(readOnly = true)
	public List<PostoDTO> buscarTodos() {
		List<Posto> postos = postoRepositorio.findAll();
		Collections.reverse(postos);
		return postos.stream().map(p -> new PostoDTO(p)).collect(Collectors.toList());
	}
}
