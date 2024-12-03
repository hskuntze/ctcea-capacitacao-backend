package br.com.sad2.capacitacao.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.CapacitadoDTO;
import br.com.sad2.capacitacao.entities.Capacitado;
import br.com.sad2.capacitacao.entities.Posto;
import br.com.sad2.capacitacao.entities.Treinamento;
import br.com.sad2.capacitacao.repositorios.CapacitadoRepositorio;
import br.com.sad2.capacitacao.repositorios.PostoRepositorio;
import br.com.sad2.capacitacao.repositorios.TreinamentoRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;
import br.com.sad2.capacitacao.servicos.excecoes.RequisicaoNaoProcessavelException;

@Service
public class CapacitadoServico {

	@Autowired
	private CapacitadoRepositorio capacitadoRepository;
	
	@Autowired
	private TreinamentoRepositorio treinamentoRepository;
	
	@Autowired
	private PostoRepositorio postoRepositorio;

	@Transactional(readOnly = true)
	public List<CapacitadoDTO> buscarTodos() {
		List<Capacitado> capacitados = capacitadoRepository.findAll();
		return capacitados.stream().map(cap -> new CapacitadoDTO(cap)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CapacitadoDTO buscarPorEmail(String email) {
		Capacitado cap = capacitadoRepository.findByEmail(email).orElseThrow(() -> new RecursoNaoEncontradoException(
				"Não foi possível localizar o capacitado com e-mail: " + email));
		return new CapacitadoDTO(cap);
	}

	@Transactional(readOnly = true)
	public CapacitadoDTO buscarPorId(Long id) {
		Capacitado cap = capacitadoRepository.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException("Não foi possível localizar o capacitado de id: " + id));
		return new CapacitadoDTO(cap);
	}

	@Transactional
	public CapacitadoDTO registrar(CapacitadoDTO dto) {
		if (dto != null) {
			Capacitado cap = new Capacitado();
			
			Treinamento t = treinamentoRepository.getReferenceById(dto.getTreinamento().getId());
			

			dtoParaEntidade(cap, dto);
			cap.setTreinamento(t);
			
			if(dto.getTipo() == 1) {
				Posto p = postoRepositorio.getReferenceById(dto.getPosto().getId());
				cap.setPosto(p);
			}
			
			cap = capacitadoRepository.save(cap);

			return new CapacitadoDTO(cap);
		} else {
			throw new RequisicaoNaoProcessavelException("Argumento nulo. Requisição improcessável.");
		}
	}

	@Transactional
	public CapacitadoDTO atualizar(Long id, CapacitadoDTO dto) {
		if (dto != null) {
			Capacitado cap = capacitadoRepository.findById(id).orElseThrow(
					() -> new RecursoNaoEncontradoException("Não foi possível localizar o capacitado de id: " + id));
			
			Treinamento t = treinamentoRepository.getReferenceById(dto.getTreinamento().getId());

			dtoParaEntidade(cap, dto);
			cap.setTreinamento(t);
			
			if(dto.getTipo() == 1) {
				Posto p = postoRepositorio.getReferenceById(dto.getPosto().getId());
				cap.setPosto(p);
			}
			
			cap = capacitadoRepository.save(cap);

			return new CapacitadoDTO(cap);
		} else {
			throw new RequisicaoNaoProcessavelException("Argumento nulo. Requisição improcessável.");
		}
	}
	
	public void deletar(Long id) {
		capacitadoRepository.deleteById(id);
	}

	private void dtoParaEntidade(Capacitado cap, CapacitadoDTO dto) {
		cap.setTipo(dto.getTipo());
		cap.setAvaliacaoPratica(dto.getAvaliacaoPratica());
		cap.setAvaliacaoTeorica(dto.getAvaliacaoTeorica());
		cap.setBrigadaMilitar(dto.getBrigadaMilitar());
		cap.setCelular(dto.getCelular());
		cap.setCertificado(dto.getCertificado());
		cap.setEmail(dto.getEmail());
		cap.setNomeGuerra(dto.getNomeGuerra());
		cap.setNomeCompleto(dto.getNomeCompleto());
		cap.setExigeNotaPratica(dto.getExigeNotaPratica());
		cap.setExigeNotaTeorica(dto.getExigeNotaTeorica());
		cap.setNotaPratica(dto.getNotaPratica());
		cap.setNotaTeorica(dto.getNotaTeorica());
		cap.setTurma(dto.getTurma());
		cap.setNumeroBi(dto.getNumeroBi());
		cap.setObservacoesAvaliacaoPratica(dto.getObservacoesAvaliacaoPratica());
		cap.setObservacoesAvaliacaoTeorica(dto.getObservacoesAvaliacaoTeorica());
		cap.setInstituicao(dto.getInstituicao());
		cap.setTipoCertificado(dto.getTipoCertificado());
		cap.setFuncao(dto.getFuncao());
	}
}