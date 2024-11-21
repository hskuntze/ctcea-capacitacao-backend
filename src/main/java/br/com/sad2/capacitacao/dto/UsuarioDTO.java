package br.com.sad2.capacitacao.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.sad2.capacitacao.entities.Usuario;

public class UsuarioDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1646080403048849453L;

	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	
	private boolean habilitado;
	private boolean registroCompleto;
	
	private Set<PerfilDTO> perfis = new HashSet<>();
	
	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.email = usuario.getEmail();
		this.habilitado = usuario.isHabilitado();
		this.registroCompleto = usuario.isRegistroCompleto();
		
		this.perfis.clear();
		usuario.getPerfis().forEach(perfil -> this.perfis.add(new PerfilDTO(perfil)));
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public boolean isRegistroCompleto() {
		return registroCompleto;
	}

	public void setRegistroCompleto(boolean registroCompleto) {
		this.registroCompleto = registroCompleto;
	}

	public Set<PerfilDTO> getPerfis() {
		return perfis;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
				+ ", habilitado=" + habilitado + "]";
	}
}
