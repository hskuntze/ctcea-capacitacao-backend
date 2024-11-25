package br.com.sad2.capacitacao.servicos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.CapacitadoDTO;
import br.com.sad2.capacitacao.dto.CapacitadoRegistroDTO;
import br.com.sad2.capacitacao.entities.Capacitado;
import br.com.sad2.capacitacao.entities.Treinamento;
import br.com.sad2.capacitacao.repositorios.CapacitadoRepository;
import br.com.sad2.capacitacao.repositorios.TreinamentoRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;
import br.com.sad2.capacitacao.servicos.excecoes.RequisicaoNaoProcessavelException;

@Service
public class CapacitadoServico {

	@Autowired
	private CapacitadoRepository capacitadoRepository;
	
	@Autowired
	private TreinamentoRepositorio treinamentoRepository;
	
	private static ZoneId ZONE_ID = ZoneId.of("America/Sao_Paulo");;

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
	public CapacitadoDTO registrar(CapacitadoRegistroDTO dto) {
		if (dto != null) {
			Capacitado cap = new Capacitado();
			
			Treinamento t = treinamentoRepository.getReferenceById(dto.getIdTreinamento());

			dtoParaEntidade(cap, dto);
			cap.getTreinamentos().add(t);
			cap = capacitadoRepository.save(cap);

			return new CapacitadoDTO(cap);
		} else {
			throw new RequisicaoNaoProcessavelException("Argumento nulo. Requisição improcessável.");
		}
	}

	@Transactional
	public CapacitadoDTO atualizar(Long id, CapacitadoRegistroDTO dto) {
		if (dto != null) {
			Capacitado cap = capacitadoRepository.findById(id).orElseThrow(
					() -> new RecursoNaoEncontradoException("Não foi possível localizar o capacitado de id: " + id));
			
			Treinamento t = treinamentoRepository.getReferenceById(dto.getIdTreinamento());

			dtoParaEntidade(cap, dto);
			cap.getTreinamentos().clear();
			cap.getTreinamentos().add(t);
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
		cap.setAvaliacaoPratica(dto.getAvaliacaoPratica());
		cap.setAvaliacaoTeorica(dto.getAvaliacaoTeorica());
		cap.setBrigada(dto.getBrigada());
		cap.setBrigadaMilitar(dto.getBrigadaMilitar());
		cap.setCelular(dto.getCelular());
		cap.setCertificado(dto.getCertificado());

		LocalDate dataInicioLocalDate = dto.getDataInicio().toInstant().atZone(ZONE_ID).toLocalDate();
		Date dataInicio = Date.from(dataInicioLocalDate.atStartOfDay(ZONE_ID).plusDays(1).toInstant());
		cap.setDataInicio(dataInicio);

		LocalDate dataFimLocalDate = dto.getDataFim().toInstant().atZone(ZONE_ID).toLocalDate();
		Date dataFim = Date.from(dataFimLocalDate.atStartOfDay(ZONE_ID).plusDays(1).toInstant());
		cap.setDataFim(dataFim);

		cap.setEmail(dto.getEmail());
		cap.setGrupo(dto.getGrupo());
		cap.setModalidade(dto.getModalidade());
		cap.setNomeGuerra(dto.getNomeGuerra());
		cap.setNomeCompleto(dto.getNomeCompleto());
		cap.setNotaPratica(dto.getNotaPratica());
		cap.setNotaTeorica(dto.getNotaTeorica());
		cap.setNumeroBi(dto.getNumeroBi());
		cap.setObservacoes(dto.getObservacoes());
		cap.setOm(dto.getOm());
		cap.setOmMilitar(dto.getOmMilitar());
		cap.setPosto(dto.getPosto());
		cap.setSubsistema(dto.getSubsistema());
		cap.setTipoCertificado(dto.getTipoCertificado());
	}
}
