package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

import br.com.sad2.capacitacao.entities.Perfil;

public class PerfilDTO implements Serializable {
	private static final long serialVersionUID = -845835373517444439L;
	
	private Long id;
	private String autorizacao;
	
	public PerfilDTO() {
	}
	
	public PerfilDTO(Perfil perfil) {
		this.id = perfil.getId();
		this.autorizacao = perfil.getAutorizacao();
	}

	public PerfilDTO(Long id, String autorizacao) {
		this.id = id;
		this.autorizacao = autorizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}

	@Override
	public String toString() {
		return "PerfilDTO [id=" + id + ", autorizacao=" + autorizacao + "]";
	}
}
