package br.com.sad2.capacitacao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe que gerencia os dados dos arquivos de Logística de Treinamento
 * inseridos no cadastro do Treinamento.
 * <p>
 * A classe "LogisticaTreinamentoFile" depende da existência de um "Treinamento".
 * <p>
 * LogisticaTreinamentoFile's não podem ser cadastrados a não ser durante o registro
 * de um Treinamento. Dessa forma, cria-se uma dependência entre essas classes.
 * (Comportamento desejado)
 */
@Entity
@Table(name = "tb_logistica_treinamento_file")
public class LogisticaTreinamentoFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileName;
	private String filePath;
	
	@Lob
	private byte[] fileContent;
	
	@ManyToOne
	@JoinColumn(name = "id_treinamento", nullable = false)
	private Treinamento treinamento;
	
	public LogisticaTreinamentoFile() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public Treinamento getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
	}
}