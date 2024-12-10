package br.com.sad2.capacitacao.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.OMDTO;
import br.com.sad2.capacitacao.entities.OM;
import br.com.sad2.capacitacao.repositorios.OMRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;

@Service
public class OMServico {

	@Autowired
	private OMRepositorio omRepositorio;

	/**
	 * Busca todas as OM's
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OMDTO> buscarTodos() {
		List<OM> oms = omRepositorio.findAll();
		return oms.stream().map(om -> new OMDTO(om)).collect(Collectors.toList());
	}

	/**
	 * Busca por uma OM através do código dela
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public OMDTO buscarPorId(Long id) {
		OM om = omRepositorio.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Não foi possível encontrar uma OM com este ID."));
		return new OMDTO(om);
	}
	
	/**
	 * Retorna todos as brigadas de forma distinta
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> buscarBDAsDistintos(){
		List<String> bdas = omRepositorio.buscarBDADistintos();
		return bdas;
	}
}
