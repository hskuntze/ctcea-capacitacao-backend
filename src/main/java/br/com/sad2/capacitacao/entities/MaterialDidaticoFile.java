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
 * Classe que gerencia os dados dos arquivos de Material Didatico do Treinamento
 * inseridos durante o cadastro do Treinamento.
 * <p>
 * A classe "MaterialDidaticoFile" depende da existência de um "Treinamento".
 * <p>
 * MaterialDidaticoFile's não podem ser cadastrados a não ser durante o registro
 * de um Treinamento. Dessa forma, cria-se uma dependência entre essas classes.
 * (Comportamento desejado)
 */
@Entity
@Table(name = "tb_material_didatico_file")
public class MaterialDidaticoFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fileName;
	private String filePath;

	@Lob
	private byte[] fileContent;

	@ManyToOne
	@JoinColumn(name = "id_treinamento", nullable = false) // Define a chave estrangeira
	private Treinamento treinamento;

	public MaterialDidaticoFile() {
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
