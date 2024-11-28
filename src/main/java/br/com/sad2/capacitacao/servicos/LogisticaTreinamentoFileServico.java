package br.com.sad2.capacitacao.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.LogisticaTreinamentoFileDTO;
import br.com.sad2.capacitacao.entities.LogisticaTreinamentoFile;
import br.com.sad2.capacitacao.repositorios.LogisticaTreinamentoFileRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;

@Service
public class LogisticaTreinamentoFileServico {

	@Autowired
	private LogisticaTreinamentoFileRepositorio logisticaTreinamentoFileRepositorio;

	@Transactional(readOnly = true)
	public LogisticaTreinamentoFileDTO buscarPorId(Long id) {
		LogisticaTreinamentoFile file = logisticaTreinamentoFileRepositorio.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(
						"Logística de treinamento com ID " + id + " não foi encontrado."));

		return new LogisticaTreinamentoFileDTO(file);
	}
}