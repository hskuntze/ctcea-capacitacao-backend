package br.com.sad2.capacitacao.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.relatorios.TreinamentoOmDTO;
import br.com.sad2.capacitacao.dto.relatorios.TreinamentoStatusDTO;
import br.com.sad2.capacitacao.repositorios.TreinamentoRepositorio;

@Service
public class RelatorioServico {

	@Autowired
	private TreinamentoRepositorio treinamentoRepositorio;
	
	/**
	 * Exibe os treinamentos agrupados por STATUS
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TreinamentoStatusDTO> relatorioQuantidadeTreinamentoPorStatus() {
		return treinamentoRepositorio.relatorioQuantidadeTreinamentoPorStatus();
	}
	
	/**
	 * Exibe os treinamentos agrupados por OM (sigla)
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TreinamentoOmDTO> relatorioQuantidadeTreinamentoPorOM() {
		return treinamentoRepositorio.relatorioQuantidadeTreinamentoPorOM();
	}
}