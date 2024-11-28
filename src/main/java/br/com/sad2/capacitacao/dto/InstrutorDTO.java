package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

import br.com.sad2.capacitacao.entities.Instrutor;

public class InstrutorDTO implements Serializable {
	private static final long serialVersionUID = 8219051918118604114L;
	
	private Long id;
	private String nome;
	private String email;
	private String contato;

	public InstrutorDTO() {
	}
	
	public InstrutorDTO(Instrutor instrutor) {
		this.id = instrutor.getId();
		this.nome = instrutor.getNome();
		this.email = instrutor.getEmail();
		this.contato = instrutor.getContato();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
}