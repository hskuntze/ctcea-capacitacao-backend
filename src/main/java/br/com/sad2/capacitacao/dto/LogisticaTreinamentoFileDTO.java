package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

import br.com.sad2.capacitacao.entities.LogisticaTreinamentoFile;

public class LogisticaTreinamentoFileDTO implements Serializable {
	private static final long serialVersionUID = 443654666077888831L;

	private Long id;
	private String fileName;
	private String filePath;
	private byte[] fileContent;
	
	public LogisticaTreinamentoFileDTO() {
	}

	public LogisticaTreinamentoFileDTO(LogisticaTreinamentoFile file) {
		this.id = file.getId();
		this.fileName = file.getFileName();
		this.filePath = file.getFilePath();
		this.fileContent = file.getFileContent();
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
}