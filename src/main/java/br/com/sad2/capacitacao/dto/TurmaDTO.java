package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

import br.com.sad2.capacitacao.entities.Turma;

public class TurmaDTO implements Serializable {
	private static final long serialVersionUID = -1611871224663846978L;

	private Long id;
	private String nome;
	
	public TurmaDTO() {
	}
	
	public TurmaDTO(Turma turma) {
		this.id = turma.getId();
		this.nome = turma.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}