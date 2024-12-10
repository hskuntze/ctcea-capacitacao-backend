package br.com.sad2.capacitacao.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.AvaliacaoTreinamentoDTO;
import br.com.sad2.capacitacao.entities.AvaliacaoTreinamento;
import br.com.sad2.capacitacao.entities.Treinamento;
import br.com.sad2.capacitacao.repositorios.AvaliacaoTreinamentoRepositorio;
import br.com.sad2.capacitacao.repositorios.TreinamentoRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;
import br.com.sad2.capacitacao.servicos.excecoes.RequisicaoNaoProcessavelException;

@Service
public class AvaliacaoTreinamentoServico {

	@Autowired
	private AvaliacaoTreinamentoRepositorio avaliacaoTreinamentoRepositorio;

	@Autowired
	private TreinamentoRepositorio treinamentoRepositorio;
	
	@Autowired
	private TreinamentoServico treinamentoServico;

	/**
	 * Busca todos os registros de Avaliação de Treinamento
	 */
	@Transactional(readOnly = true)
	public List<AvaliacaoTreinamentoDTO> buscarTodos() {
		List<AvaliacaoTreinamento> avaliacoes = avaliacaoTreinamentoRepositorio.findAll();
		return avaliacoes.stream().map(a -> new AvaliacaoTreinamentoDTO(a)).collect(Collectors.toList());
	}

	/**
	 * Busca uma avaliação baseado no ID
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public AvaliacaoTreinamentoDTO buscarPorId(Long id) {
		AvaliacaoTreinamento avaliacao = avaliacaoTreinamentoRepositorio.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(
						"Avaliação de treinamento com ID " + id + " não foi encontrado"));
		return new AvaliacaoTreinamentoDTO(avaliacao);
	}

	/**
	 * Registra uma avaliação
	 * @param dto
	 */
	@Transactional
	public AvaliacaoTreinamentoDTO registrar(AvaliacaoTreinamentoDTO dto) {
		if (dto.getTreinamento() != null) {
			Long idTreinamento = dto.getTreinamento().getId();
			Treinamento t = treinamentoRepositorio.getReferenceById(idTreinamento);

			if (t != null) {
				AvaliacaoTreinamento at = new AvaliacaoTreinamento();
				
				t = treinamentoServico.atualizar(dto.getTreinamento().getId(), dto.getTreinamento());
				
				dtoParaEntidade(at, dto);

				at.setTreinamento(t);
				at = avaliacaoTreinamentoRepositorio.save(at);

				return new AvaliacaoTreinamentoDTO(at);
			} else {
				throw new RecursoNaoEncontradoException("Treinamento com ID " + idTreinamento + " não foi encontrado");
			}
		} else {
			throw new RequisicaoNaoProcessavelException(
					"Não é possível registrar uma avaliação sem um treinamento selecionado.");
		}
	}

	/**
	 * Atualiza o registro de uma avaliação
	 * @param id
	 * @param dto
	 */
	@Transactional
	public AvaliacaoTreinamentoDTO atualizar(Long id, AvaliacaoTreinamentoDTO dto) {
		if (dto.getTreinamento() != null) {
			Long idTreinamento = dto.getTreinamento().getId();
			Treinamento t = treinamentoRepositorio.getReferenceById(idTreinamento);

			if (t != null) {
				AvaliacaoTreinamento at = avaliacaoTreinamentoRepositorio.findById(id)
						.orElseThrow(() -> new RecursoNaoEncontradoException(
								"Avaliação de treinamento com ID " + id + " não foi encontrado"));
				
				t = treinamentoServico.atualizar(dto.getTreinamento().getId(), dto.getTreinamento());
				
				dtoParaEntidade(at, dto);

				at.setTreinamento(t);
				at = avaliacaoTreinamentoRepositorio.save(at);

				return new AvaliacaoTreinamentoDTO(at);
			} else {
				throw new RecursoNaoEncontradoException("Treinamento com ID " + idTreinamento + " não foi encontrado");
			}

		} else {
			throw new RequisicaoNaoProcessavelException(
					"Não é possível registrar uma avaliação sem um treinamento selecionado.");
		}
	}

	public void deletar(Long id) {
		avaliacaoTreinamentoRepositorio.deleteById(id);
	}

	private void dtoParaEntidade(AvaliacaoTreinamento at, AvaliacaoTreinamentoDTO dto) {
		at.setAbrangeuTodosObjetivos(dto.getAbrangeuTodosObjetivos());
		at.setApostilaAtualizada(dto.getApostilaAtualizada());
		at.setApostilaObjetiva(dto.getApostilaObjetiva());
		at.setAvaliacaoGeralTreinamento(dto.getAvaliacaoGeralTreinamento());
		at.setQualidadeMaterial(dto.getQualidadeMaterial());
		at.setQuestoesClaras(dto.getQuestoesClaras());
		at.setQuestoesRelacionadas(dto.getQuestoesRelacionadas());
		at.setNomeResponsavel(dto.getNomeResponsavel());
		at.setFuncaoResponsavel(dto.getFuncaoResponsavel());
		at.setComentariosSugestoes(dto.getComentariosSugestoes());
		at.setComentariosSugestoes(dto.getComentariosSugestoes());
	}
}
