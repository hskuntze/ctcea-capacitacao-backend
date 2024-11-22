package br.com.sad2.capacitacao.servicos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.TreinamentoDTO;
import br.com.sad2.capacitacao.dto.UsuarioBasicoDTO;
import br.com.sad2.capacitacao.entities.Treinamento;
import br.com.sad2.capacitacao.entities.Usuario;
import br.com.sad2.capacitacao.repositorios.TreinamentoRepositorio;
import br.com.sad2.capacitacao.repositorios.UsuarioRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;
import br.com.sad2.capacitacao.servicos.excecoes.RequisicaoNaoProcessavelException;

@Service
public class TreinamentoServico {

	@Autowired
	private TreinamentoRepositorio treinamentoRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	/**
	 * Método que retorna todos os resultados de TreinamentoDTO
	 * @return TreinamentoDTO
	 */
	@Transactional(readOnly = true)
	public List<TreinamentoDTO> buscarTodos() {
		List<Treinamento> treinamentos = treinamentoRepositorio.findAll();
		return treinamentos.stream().map(t -> new TreinamentoDTO(t)).collect(Collectors.toList());
	}

	/**
	 * Método que retorna todos os Treinamentos com os usuários a ele relacionados
	 * @return TreinamentoDTO
	 */
	@Transactional(readOnly = true)
	public List<TreinamentoDTO> buscarTodosComUsuario() {
		List<Treinamento> treinamentos = treinamentoRepositorio.findAll();
		List<TreinamentoDTO> treinamentosDto = treinamentos.stream().map(t -> new TreinamentoDTO(t))
				.collect(Collectors.toList());

		treinamentosDto.forEach(t -> {
			Set<UsuarioBasicoDTO> usuariosRelacionados = usuarioRepositorio.findUsuarioByTreinamento(t.getId()).stream()
					.map(user -> new UsuarioBasicoDTO(user)).collect(Collectors.toSet());

			usuariosRelacionados.forEach(u -> t.getUsuarios().add(u));
		});

		return treinamentosDto;
	}
	
	/**
	 * Método que retorna um Treinamento baseado em seu identificador único
	 * @return TreinamentoDTO
	 */
	@Transactional(readOnly = true)
	public TreinamentoDTO buscarPorId(Long id) {
		Treinamento t = treinamentoRepositorio.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException("Treinamento com ID " + id + " não foi encontrado."));
		return new TreinamentoDTO(t);
	}

	/**
	 * Método que retorna um Treinamento com os usuários a ele relacionado, baseado em seu identificador único
	 * @return TreinamentoDTO
	 */
	@Transactional(readOnly = true)
	public TreinamentoDTO buscarPorIdComUsuarios(Long id) {
		Treinamento t = treinamentoRepositorio.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException("Treinamento com ID " + id + " não foi encontrado."));

		TreinamentoDTO dto = new TreinamentoDTO(t);

		Set<UsuarioBasicoDTO> usuariosRelacionados = usuarioRepositorio.findUsuarioByTreinamento(id).stream()
				.map(user -> new UsuarioBasicoDTO(user)).collect(Collectors.toSet());

		usuariosRelacionados.forEach(user -> dto.getUsuarios().add(user));
		return dto;
	}

	/**
	 * Registra um novo Treinamento
	 * @return TreinamentoDTO
	 */
	@Transactional
	public TreinamentoDTO registrar(TreinamentoDTO dto) {
		if (dto != null) {
			Treinamento treinamento = new Treinamento();

			dtoParaEntidade(treinamento, dto);
			treinamento = treinamentoRepositorio.save(treinamento);

			return new TreinamentoDTO(treinamento);
		} else {
			throw new RequisicaoNaoProcessavelException("Argumento nulo. Requisição improcessável.");
		}
	}

	/**
	 * Atualiza um Treinamento
	 * @return TreinamentoDTO
	 */
	@Transactional
	public TreinamentoDTO atualizar(Long id, TreinamentoDTO dto) {
		if (dto != null) {
			Treinamento treinamento = treinamentoRepositorio.findById(id).orElseThrow(
					() -> new RecursoNaoEncontradoException("Treinamento com ID " + id + " não foi encontrado."));

			dtoParaEntidade(treinamento, dto);
			treinamento = treinamentoRepositorio.save(treinamento);

			return new TreinamentoDTO(treinamento);
		} else {
			throw new RequisicaoNaoProcessavelException("Argumento nulo. Requisição improcessável.");
		}
	}
	
	public void deletar(Long id) {
		treinamentoRepositorio.deleteById(id);
	}
	
	/**
	 * ----------- ESSA LÓGICA SERÁ ALTERADA PARA OS PARTICIPANTES ------------
	 * Existe a diferença entre o usuário técnico do sistema (que opera o sistema), e o
	 * usuário que faz parte dos treinamentos que são os "capacitados".
	 * 	
	 * Insere um usuário em um Treinamento
	 * @return TreinamentoDTO
	 */
	@Transactional
	public TreinamentoDTO inserirUsuarioEmTreinamento(Long idTreinamento, Long idUsuario) {
		Treinamento treinamento = treinamentoRepositorio.findById(idTreinamento).orElseThrow(
				() -> new RecursoNaoEncontradoException("Treinamento com ID " + idTreinamento + " não foi encontrado."));
		
		Usuario usuario = usuarioRepositorio.findById(idUsuario).orElseThrow(
				() -> new RecursoNaoEncontradoException("Usuário com ID " + idUsuario + " não foi encontrado."));
		
		usuario.getTreinamentos().add(treinamento);
		usuario = usuarioRepositorio.save(usuario);
		
		Set<UsuarioBasicoDTO> usuariosRelacionados = usuarioRepositorio.findUsuarioByTreinamento(idTreinamento).stream()
				.map(user -> new UsuarioBasicoDTO(user)).collect(Collectors.toSet());
		
		TreinamentoDTO dto = new TreinamentoDTO(treinamento);
		usuariosRelacionados.forEach(user -> dto.getUsuarios().add(user));
		return dto;
	}
	
	/**
	 * Remove um usuário de um Treinamento
	 * @return TreinamentoDTO
	 */
	@Transactional
	public TreinamentoDTO removerUsuarioEmTreinamento(Long idTreinamento, Long idUsuario) {
		Treinamento treinamento = treinamentoRepositorio.findById(idTreinamento).orElseThrow(
				() -> new RecursoNaoEncontradoException("Treinamento com ID " + idTreinamento + " não foi encontrado."));
		
		Usuario usuario = usuarioRepositorio.findById(idUsuario).orElseThrow(
				() -> new RecursoNaoEncontradoException("Usuário com ID " + idUsuario + " não foi encontrado."));
		
		usuario.getTreinamentos().remove(treinamento);
		usuario = usuarioRepositorio.save(usuario);
		
		Set<UsuarioBasicoDTO> usuariosRelacionados = usuarioRepositorio.findUsuarioByTreinamento(idTreinamento).stream()
				.map(user -> new UsuarioBasicoDTO(user)).collect(Collectors.toSet());
		
		TreinamentoDTO dto = new TreinamentoDTO(treinamento);
		usuariosRelacionados.forEach(user -> dto.getUsuarios().add(user));
		return dto;
	}

	private void dtoParaEntidade(Treinamento treinamento, TreinamentoDTO dto) {
		ZoneId zone = ZoneId.of("America/Sao_Paulo");
		
		treinamento.setSad(dto.getSad());
		treinamento.setMaterial(dto.getMaterial());
		treinamento.setTreinamento(dto.getTreinamento());
		treinamento.setTipo(dto.getTipo());
		treinamento.setSubsistema(dto.getSubsistema());
		treinamento.setModalidade(dto.getModalidade());
		treinamento.setBrigada(dto.getBrigada());
		treinamento.setOm(dto.getOm());
		treinamento.setGrupo(dto.getGrupo());
		treinamento.setExecutor(dto.getExecutor());
		treinamento.setInstituicao(dto.getInstituicao());
		
		LocalDate dataInicioLocalDate = dto.getDataInicio().toInstant().atZone(zone).toLocalDate();
		Date dataInicio = Date.from(dataInicioLocalDate.atStartOfDay(zone).plusDays(1).toInstant());
		treinamento.setDataInicio(dataInicio);
		
		System.out.println(dataInicio);
		
		LocalDate dataFimLocalDate = dto.getDataFim().toInstant().atZone(zone).toLocalDate();
		Date dataFim = Date.from(dataFimLocalDate.atStartOfDay(zone).plusDays(1).toInstant());
		treinamento.setDataFim(dataFim);
		
		treinamento.setVagas(dto.getVagas());
		treinamento.setStatus(dto.getStatus());
		treinamento.setAvaliacaoPratica(dto.getAvaliacaoPratica());
		treinamento.setAvaliacaoTeorica(dto.getAvaliacaoTeorica());
		treinamento.setNomeInstrutores(dto.getNomeInstrutores());
		treinamento.setContatoInstrutores(dto.getContatoInstrutores());
		treinamento.setCertificado(dto.getCertificado());
		treinamento.setLogisticaTreinamento(dto.getLogisticaTreinamento());
		treinamento.setNivelamento(dto.getNivelamento());
		treinamento.setCargaHoraria(dto.getCargaHoraria());
		treinamento.setPublicoAlvo(dto.getPublicoAlvo());
		treinamento.setDescricaoAtividade(dto.getDescricaoAtividade());
		treinamento.setMaterialDidatico(dto.getMaterialDidatico());
		treinamento.setObservacoes(dto.getObservacoes());
		treinamento.setPreRequisitos(dto.getPreRequisitos());
	}
}
