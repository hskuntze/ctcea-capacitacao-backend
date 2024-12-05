package br.com.sad2.capacitacao.controladores;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sad2.capacitacao.dto.LogisticaTreinamentoFileDTO;
import br.com.sad2.capacitacao.dto.MaterialDidaticoFileDTO;
import br.com.sad2.capacitacao.dto.TreinamentoDTO;
import br.com.sad2.capacitacao.entities.TreinamentoCapacitadoFiltro;
import br.com.sad2.capacitacao.servicos.LogisticaTreinamentoFileServico;
import br.com.sad2.capacitacao.servicos.MaterialDidaticoFileServico;
import br.com.sad2.capacitacao.servicos.TreinamentoServico;

@RestController
@RequestMapping(value = "/treinamentos")
public class TreinamentoControlador {

	@Autowired
	private TreinamentoServico treinamentoServico;

	@Autowired
	private MaterialDidaticoFileServico mdfServico;

	@Autowired
	private LogisticaTreinamentoFileServico ltfServico;

	/**
	 * --------- GETS ---------
	 */
	@GetMapping
	public ResponseEntity<List<TreinamentoDTO>> buscarTodos() {
		return ResponseEntity.ok().body(treinamentoServico.buscarTodos());
	}

	@GetMapping(value = "/filtrar")
	public ResponseEntity<Set<TreinamentoCapacitadoFiltro>> buscarFiltro(
			@RequestParam(defaultValue = "") String treinamento, @RequestParam(defaultValue = "") String sigla,
			@RequestParam(defaultValue = "") String bda, @RequestParam(defaultValue = "") String nomeCompleto, @RequestParam(defaultValue = "-1") Integer status) {
		Integer statusFilter = (status == -1) ? null : status;
		return ResponseEntity.ok().body(treinamentoServico.filtrarTreinamentos(treinamento, sigla, bda, nomeCompleto, statusFilter));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TreinamentoDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(treinamentoServico.buscarPorId(id));
	}

	@GetMapping(value = "/download/materialDidatico/{id}")
	public ResponseEntity<byte[]> downloadMaterialDidatico(@PathVariable Long id) {
		MaterialDidaticoFileDTO dto = mdfServico.buscarPorId(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDisposition(ContentDisposition.inline().filename(dto.getFileName()).build());

		byte[] fileContent;

		try {
			if (dto.getFilePath() != null) {
				Path filePath = Paths.get(dto.getFilePath());
				fileContent = Files.readAllBytes(filePath);
			} else {
				fileContent = dto.getFileContent();
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler o arquivo.", e);
		}

		return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	}

	@GetMapping(value = "/download/logisticaTreinamento/{id}")
	public ResponseEntity<byte[]> downloadLogisticaTreinamento(@PathVariable Long id) {
		LogisticaTreinamentoFileDTO dto = ltfServico.buscarPorId(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDisposition(ContentDisposition.inline().filename(dto.getFileName()).build());

		byte[] fileContent;

		try {
			if (dto.getFilePath() != null) {
				Path filePath = Paths.get(dto.getFilePath());
				fileContent = Files.readAllBytes(filePath);
			} else {
				fileContent = dto.getFileContent();
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler o arquivo.", e);
		}

		return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	}

	/**
	 * --------- POSTS ---------
	 */
	@PostMapping(value = "/registrar")
	public ResponseEntity<TreinamentoDTO> registrar(@RequestBody TreinamentoDTO dto) {
		TreinamentoDTO treinamento = treinamentoServico.registrar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(treinamento.getId())
				.toUri();

		return ResponseEntity.created(uri).body(treinamento);
	}

	@PostMapping(value = "/upload/materialDidatico")
	public ResponseEntity<String> uploadMaterialDidatico(@RequestParam("file") MultipartFile file,
			@RequestParam("id") Long treinamentoId) {
		treinamentoServico.uploadMaterialDidatico(file, treinamentoId);
		return ResponseEntity.ok().body("Sucesso");
	}

	@PostMapping(value = "/upload/logisticaTreinamento")
	public ResponseEntity<String> uploadLogisticaTreinamento(@RequestParam("file") MultipartFile file,
			@RequestParam("id") Long treinamentoId) {
		treinamentoServico.uploadLogisticaTreinamento(file, treinamentoId);
		return ResponseEntity.ok().body("Sucesso");
	}

	/**
	 * --------- PUTS ---------
	 */
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<TreinamentoDTO> atualizar(@PathVariable Long id, @RequestBody TreinamentoDTO dto) {
		TreinamentoDTO treinamento = treinamentoServico.atualizar(id, dto);
		return ResponseEntity.ok().body(treinamento);
	}

	/**
	 * --------- DELETE ---------
	 */
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		treinamentoServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/deletar/materialDidatico/{id}")
	public ResponseEntity<Void> deletarMaterialDidatico(@PathVariable Long id) {
		treinamentoServico.deleteMaterialDidatico(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/deletar/logisticaTreinamento/{id}")
	public ResponseEntity<Void> deletarLogisticaTreinamento(@PathVariable Long id) {
		treinamentoServico.deleteLogisticaTreinamento(id);
		return ResponseEntity.noContent().build();
	}
}
