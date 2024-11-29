package br.com.sad2.capacitacao.servicos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.OcorrenciaDTO;
import br.com.sad2.capacitacao.entities.Ocorrencia;
import br.com.sad2.capacitacao.entities.Treinamento;
import br.com.sad2.capacitacao.repositorios.OcorrenciaRepositorio;
import br.com.sad2.capacitacao.repositorios.TreinamentoRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;

@Service
public class OcorrenciaServico {

	@Autowired
	private OcorrenciaRepositorio ocorrenciaRepositorio;
	
	@Autowired
	private TreinamentoRepositorio treinamentoRepositorio;
	
	private static final ZoneId ZONE = ZoneId.of("America/Sao_Paulo");
	
	@Transactional(readOnly = true)
	public List<OcorrenciaDTO> buscarTodos() {
		List<Ocorrencia> lista = ocorrenciaRepositorio.findAll();
		return lista.stream().map(o -> new OcorrenciaDTO(o)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public OcorrenciaDTO buscarPorId(Long id) {
		Ocorrencia o = ocorrenciaRepositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Ocorrência com ID " + id + " não foi encontrada."));
		return new OcorrenciaDTO(o);
	}
	
	@Transactional
	public OcorrenciaDTO registrar(OcorrenciaDTO dto) {
		Ocorrencia o = new Ocorrencia();
		Treinamento t = treinamentoRepositorio.getReferenceById(dto.getTreinamento().getId());
		
		dtoParaEntidade(o, dto);
		o.setTreinamento(t);
		
		o = ocorrenciaRepositorio.save(o);
		
		return new OcorrenciaDTO(o);
	}
	
	@Transactional
	public OcorrenciaDTO atualizar(Long id, OcorrenciaDTO dto) {
		Ocorrencia o = ocorrenciaRepositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Ocorrência com ID " + id + " não foi encontrada."));
		Treinamento t = treinamentoRepositorio.getReferenceById(dto.getTreinamento().getId());
		
		dtoParaEntidade(o, dto);
		o.setTreinamento(t);
		
		o = ocorrenciaRepositorio.save(o);
		
		return new OcorrenciaDTO(o);
	}
	
	public void deletar(Long id) {
		ocorrenciaRepositorio.deleteById(id);
	}

	private void dtoParaEntidade(Ocorrencia o, OcorrenciaDTO dto) {
	    o.setTitulo(dto.getTitulo());
	    o.setDescricaoOcorrencia(dto.getDescricaoOcorrencia());
		
		LocalDate dataOcorrenciaLocalDate = dto.getDataOcorrencia().toInstant().atZone(ZONE).toLocalDate();
		Date dataOcorrencia = Date.from(dataOcorrenciaLocalDate.atStartOfDay(ZONE).plusDays(1).toInstant());
	    o.setDataOcorrencia(dataOcorrencia);
	    
	    o.setTipoOcorrencia(dto.getTipoOcorrencia());
	    o.setDescricaoTipoOutros(dto.getDescricaoTipoOutros());
	    o.setImpactoOcorrencia(dto.getImpactoOcorrencia());
	    o.setNivelImpacto(dto.getNivelImpacto());
	    o.setDescricaoImpacto(dto.getDescricaoImpacto());
	    o.setStatusClassificacao(dto.getStatusClassificacao());
	    o.setSolucaoAdotada(dto.getSolucaoAdotada());
	    
	    LocalDate dataSolucaoLocalDate = dto.getDataSolucao().toInstant().atZone(ZONE).toLocalDate();
	    Date dataSolucao = Date.from(dataSolucaoLocalDate.atStartOfDay(ZONE).plusDays(1).toInstant());
	    o.setDataSolucao(dataSolucao);
	    
	    o.setNomeResponsavelSolucao(dto.getNomeResponsavelSolucao());
	    o.setContatoResponsavelSolucao(dto.getContatoResponsavelSolucao());
	    o.setInstituicaoResponsavelSolucao(dto.getInstituicaoResponsavelSolucao());
	    o.setDescricaoClassificacao(dto.getDescricaoClassificacao());
	    o.setProbabilidadeRecorrencia(dto.getProbabilidadeRecorrencia());
	    o.setDescricaoLicoesAprendidas(dto.getDescricaoLicoesAprendidas());
	    o.setNomeResponsavelOcorrencia(dto.getNomeResponsavelOcorrencia());
	    o.setContatoResponsavelOcorrencia(dto.getContatoResponsavelOcorrencia());
	    o.setInstituicaoResponsavelOcorrencia(dto.getInstituicaoResponsavelOcorrencia());
	    
	    LocalDate dataRegistroLocalDate = dto.getDataRegistro().toInstant().atZone(ZONE).toLocalDate();
	    Date dataRegistro = Date.from(dataRegistroLocalDate.atStartOfDay(ZONE).plusDays(1).toInstant());
	    o.setDataRegistro(dataRegistro);
	    
	    o.setObservacoesGerais(dto.getObservacoesGerais());
	}
}
