package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

import br.com.sad2.capacitacao.entities.Instrutor;

public class InstrutorDTO implements Serializable {
	private static final long serialVersionUID = 8219051918118604114L;
	
	private Long id;
	private String nome;
	private String email;
	private String contato;
	private Integer engajamento;
	private Integer clareza;
	private Integer nivelConhecimento;
	private Integer capacidadeResposta;
	private Integer capacidadeGerirAula;

	public InstrutorDTO() {
	}
	
	public InstrutorDTO(Instrutor instrutor) {
		this.id = instrutor.getId();
		this.nome = instrutor.getNome();
		this.email = instrutor.getEmail();
		this.contato = instrutor.getContato();
		this.engajamento = instrutor.getEngajamento();
		this.clareza = instrutor.getClareza();
		this.nivelConhecimento = instrutor.getNivelConhecimento();
		this.capacidadeResposta = instrutor.getCapacidadeResposta();
		this.capacidadeGerirAula = instrutor.getCapacidadeGerirAula();
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

	public Integer getEngajamento() {
		return engajamento;
	}

	public void setEngajamento(Integer engajamento) {
		this.engajamento = engajamento;
	}

	public Integer getClareza() {
		return clareza;
	}

	public void setClareza(Integer clareza) {
		this.clareza = clareza;
	}

	public Integer getNivelConhecimento() {
		return nivelConhecimento;
	}

	public void setNivelConhecimento(Integer nivelConhecimento) {
		this.nivelConhecimento = nivelConhecimento;
	}

	public Integer getCapacidadeResposta() {
		return capacidadeResposta;
	}

	public void setCapacidadeResposta(Integer capacidadeResposta) {
		this.capacidadeResposta = capacidadeResposta;
	}

	public Integer getCapacidadeGerirAula() {
		return capacidadeGerirAula;
	}

	public void setCapacidadeGerirAula(Integer capacidadeGerirAula) {
		this.capacidadeGerirAula = capacidadeGerirAula;
	}
}