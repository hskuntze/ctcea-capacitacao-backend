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
	private Integer tipo;
	private String nomeGuerra;
	private String identidade;
	private String telefone;
	private String instituicao;
	
	private PostoDTO posto;
	
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
		this.telefone = usuario.getTelefone();
		this.tipo = usuario.getTipo();
		this.identidade = usuario.getIdentidade();
		this.nomeGuerra = usuario.getNomeGuerra();
		this.instituicao = usuario.getInstituicao();
		
		if(usuario.getPosto() != null) {
			this.posto = new PostoDTO(usuario.getPosto());
		}
		
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

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public PostoDTO getPosto() {
		return posto;
	}

	public void setPosto(PostoDTO posto) {
		this.posto = posto;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
				+ ", habilitado=" + habilitado + "]";
	}
}
