package br.com.sad2.capacitacao.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.MaterialDidaticoFileDTO;
import br.com.sad2.capacitacao.entities.MaterialDidaticoFile;
import br.com.sad2.capacitacao.repositorios.MaterialDidaticoFileRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;

@Service
public class MaterialDidaticoFileServico {

	@Autowired
	private MaterialDidaticoFileRepositorio materialDidaticoFileRepositorio;

	@Transactional(readOnly = true)
	public MaterialDidaticoFileDTO buscarPorId(Long id) {
		MaterialDidaticoFile file = materialDidaticoFileRepositorio.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException("Material didático com ID " + id + " não foi encontrado."));
		
		return new MaterialDidaticoFileDTO(file);
	}
}
