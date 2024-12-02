package br.com.sad2.capacitacao.servicos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.sad2.capacitacao.dto.InstrutorDTO;
import br.com.sad2.capacitacao.dto.TreinamentoDTO;
import br.com.sad2.capacitacao.dto.TurmaDTO;
import br.com.sad2.capacitacao.entities.Instrutor;
import br.com.sad2.capacitacao.entities.LogisticaTreinamentoFile;
import br.com.sad2.capacitacao.entities.MaterialDidaticoFile;
import br.com.sad2.capacitacao.entities.Treinamento;
import br.com.sad2.capacitacao.entities.Turma;
import br.com.sad2.capacitacao.repositorios.InstrutorRepositorio;
import br.com.sad2.capacitacao.repositorios.LogisticaTreinamentoFileRepositorio;
import br.com.sad2.capacitacao.repositorios.MaterialDidaticoFileRepositorio;
import br.com.sad2.capacitacao.repositorios.TreinamentoRepositorio;
import br.com.sad2.capacitacao.repositorios.TurmaRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;
import br.com.sad2.capacitacao.servicos.excecoes.RequisicaoNaoProcessavelException;

@Service
public class TreinamentoServico {

	@Autowired
	private TreinamentoRepositorio treinamentoRepositorio;

	@Autowired
	private InstrutorRepositorio instrutorRepositorio;

	@Autowired
	private TurmaRepositorio turmaRepositorio;

	@Autowired
	private MaterialDidaticoFileRepositorio materialDidaticoFileRepositorio;

	@Autowired
	private LogisticaTreinamentoFileRepositorio logisticaTreinamentoRepositorio;

	//private static final Long MAX_FILE_SIZE = 10485760L; // 10MiB em bytes
	private static final Long MAX_FILE_SIZE = 1572864L; //1.5MiB em bytes
	private static final String CURRENT_DIR = System.getProperty("user.dir");
	private static final Path CURRENT_PATH = Paths.get(CURRENT_DIR);
	private static final Path PARENT_PATH = CURRENT_PATH.getParent();
	private static final Path UPLOAD_PATH = PARENT_PATH.resolve("uploads");

	/**
	 * Método que retorna todos os resultados de TreinamentoDTO
	 * 
	 * @return TreinamentoDTO
	 */
	@Transactional(readOnly = true)
	public List<TreinamentoDTO> buscarTodos() {
		List<Treinamento> treinamentos = treinamentoRepositorio.findAll();
		return treinamentos.stream().map(t -> new TreinamentoDTO(t)).collect(Collectors.toList());
	}

	/**
	 * Método que retorna um Treinamento baseado em seu identificador único
	 * 
	 * @return TreinamentoDTO
	 */
	@Transactional(readOnly = true)
	public TreinamentoDTO buscarPorId(Long id) {
		Treinamento t = treinamentoRepositorio.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException("Treinamento com ID " + id + " não foi encontrado."));
		return new TreinamentoDTO(t);
	}

	/**
	 * Registra um novo Treinamento
	 * 
	 * @return TreinamentoDTO
	 */
	@Transactional
	public TreinamentoDTO registrar(TreinamentoDTO dto) {
		if (dto != null) {
			Treinamento treinamento = new Treinamento();

			dtoParaEntidade(treinamento, dto);

			for (InstrutorDTO i : dto.getInstrutores()) {
				Instrutor it = new Instrutor();
				instrutorDtoParaEntidade(it, i);
				it.setTreinamento(treinamento);
				treinamento.getInstrutores().add(it);
			}

			for (TurmaDTO t : dto.getTurmas()) {
				Turma tu = new Turma(t.getNome());
				tu.setTreinamento(treinamento);
				treinamento.getTurmas().add(tu);
			}

			treinamento = treinamentoRepositorio.save(treinamento);

			return new TreinamentoDTO(treinamento);
		} else {
			throw new RequisicaoNaoProcessavelException("Argumento nulo. Requisição improcessável.");
		}
	}

	/**
	 * Atualiza um Treinamento
	 * 
	 * @return TreinamentoDTO
	 */
	@Transactional
	public TreinamentoDTO atualizar(Long id, TreinamentoDTO dto) {
		if (dto != null) {
			Treinamento treinamento = treinamentoRepositorio.findById(id).orElseThrow(
					() -> new RecursoNaoEncontradoException("Treinamento com ID " + id + " não foi encontrado."));

			dtoParaEntidade(treinamento, dto);

			treinamento.getInstrutores().clear();
			for (InstrutorDTO i : dto.getInstrutores()) {
				if (i.getId() != null) {
					Instrutor it = instrutorRepositorio.getReferenceById(i.getId());

					instrutorDtoParaEntidade(it, i);
					it.setTreinamento(treinamento);
					treinamento.getInstrutores().add(it);
				} else {
					Instrutor it = new Instrutor(i.getNome(), i.getEmail(), i.getContato());

					it.setTreinamento(treinamento);
					treinamento.getInstrutores().add(it);
				}
			}

			treinamento.getTurmas().clear();
			for (TurmaDTO t : dto.getTurmas()) {
				if (t.getId() != null) {
					Turma tu = turmaRepositorio.getReferenceById(t.getId());

					tu.setNome(t.getNome());

					tu.setTreinamento(treinamento);
					treinamento.getTurmas().add(tu);
				} else {
					Turma tu = new Turma(t.getNome());

					tu.setTreinamento(treinamento);
					treinamento.getTurmas().add(tu);
				}
			}

			treinamento = treinamentoRepositorio.save(treinamento);

			return new TreinamentoDTO(treinamento);
		} else {
			throw new RequisicaoNaoProcessavelException("Argumento nulo. Requisição improcessável.");
		}
	}

	/**
	 * Função para realizar o upload de arquivo de Material Didático para o banco de
	 * dados
	 * 
	 * @param file
	 * @param idTreinamento
	 */
	@Transactional
	public void uploadMaterialDidatico(MultipartFile file, Long idTreinamento) {
		if (file.isEmpty()) {
			throw new RequisicaoNaoProcessavelException("O arquivo está vazio.");
		}

		Treinamento treinamento = treinamentoRepositorio.findById(idTreinamento)
				.orElseThrow(() -> new RecursoNaoEncontradoException(
						"Treinamento com ID " + idTreinamento + " não foi encontrado."));

		MaterialDidaticoFile mdf = new MaterialDidaticoFile();
		mdf.setFileName(file.getOriginalFilename());
		mdf.setTreinamento(treinamento);

		try {
			if (file.getSize() > MAX_FILE_SIZE) {
				Path filePath = UPLOAD_PATH.resolve(file.getOriginalFilename());
				Files.write(filePath, file.getBytes());
				mdf.setFilePath(filePath.toString());
			} else {
				mdf.setFileContent(file.getBytes());
			}
			materialDidaticoFileRepositorio.save(mdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Função para realizar o upload de arquivo de Logística de Treinamento para o
	 * banco de dados
	 * 
	 * @param file
	 * @param idTreinamento
	 */
	@Transactional
	public void uploadLogisticaTreinamento(MultipartFile file, Long idTreinamento) {
		Treinamento treinamento = treinamentoRepositorio.findById(idTreinamento)
				.orElseThrow(() -> new RecursoNaoEncontradoException(
						"Treinamento com ID " + idTreinamento + " não foi encontrado."));

		LogisticaTreinamentoFile ltf = new LogisticaTreinamentoFile();
		ltf.setFileName(file.getOriginalFilename());
		ltf.setTreinamento(treinamento);
		
		try {
			if (file.getSize() > MAX_FILE_SIZE) {
				Path filePath = UPLOAD_PATH.resolve(file.getOriginalFilename());
				Files.write(filePath, file.getBytes());
				ltf.setFilePath(filePath.toString());
			} else {
				ltf.setFileContent(file.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		logisticaTreinamentoRepositorio.save(ltf);
	}

	public void deleteMaterialDidatico(Long id) {
		materialDidaticoFileRepositorio.deleteById(id);
	}

	public void deleteLogisticaTreinamento(Long id) {
		logisticaTreinamentoRepositorio.deleteById(id);
	}

	public void deletar(Long id) {
		treinamentoRepositorio.deleteById(id);
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

		LocalDate dataFimLocalDate = dto.getDataFim().toInstant().atZone(zone).toLocalDate();
		Date dataFim = Date.from(dataFimLocalDate.atStartOfDay(zone).plusDays(1).toInstant());
		treinamento.setDataFim(dataFim);

		treinamento.setVagas(dto.getVagas());
		treinamento.setStatus(dto.getStatus());
		treinamento.setAvaliacaoPratica(dto.getAvaliacaoPratica());
		treinamento.setAvaliacaoTeorica(dto.getAvaliacaoTeorica());
		treinamento.setCertificado(dto.getCertificado());
		treinamento.setLogisticaTreinamento(dto.getLogisticaTreinamento());
		treinamento.setNivelamento(dto.getNivelamento());
		treinamento.setCargaHoraria(dto.getCargaHoraria());
		treinamento.setPublicoAlvo(dto.getPublicoAlvo());
		treinamento.setDescricaoAtividade(dto.getDescricaoAtividade());
		treinamento.setObservacoes(dto.getObservacoes());
		treinamento.setPreRequisitos(dto.getPreRequisitos());
		treinamento.setDescNivelamento(dto.getDescNivelamento());
	}
	
	private void instrutorDtoParaEntidade(Instrutor instrutor, InstrutorDTO dto) {
		instrutor.setNome(dto.getNome());
		instrutor.setEmail(dto.getEmail());
		instrutor.setNivelConhecimento(dto.getNivelConhecimento());
		instrutor.setCapacidadeGerirAula(dto.getCapacidadeGerirAula());
		instrutor.setCapacidadeResposta(dto.getCapacidadeResposta());
		instrutor.setClareza(dto.getClareza());
		instrutor.setEngajamento(dto.getEngajamento());
		instrutor.setContato(dto.getContato());
	}
}
