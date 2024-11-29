package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

import br.com.sad2.capacitacao.entities.MaterialDidaticoFile;

public class MaterialDidaticoFileDTO implements Serializable {
	private static final long serialVersionUID = -141376975101047536L;
	
	private Long id;
	private String fileName;
	private String filePath;
	private byte[] fileContent;
	
	public MaterialDidaticoFileDTO() {
	}
	
	public MaterialDidaticoFileDTO(MaterialDidaticoFile file) {
		this.id = file.getId();
		if(file.getFilePath() != null) {
			this.filePath = file.getFilePath();
		}
		this.fileName = file.getFileName();
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