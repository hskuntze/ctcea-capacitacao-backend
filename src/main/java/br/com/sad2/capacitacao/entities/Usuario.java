package br.com.sad2.capacitacao.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable, UserDetails {
	private static final long serialVersionUID = -5111831719953998168L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	//Tipo = 1 - militar ou 2 - civil
	private Integer tipo;
	private String nomeGuerra;
	private String identidade;
	private String telefone;
	private String instituicao;
	
	@ManyToOne
    @JoinColumn(name = "id_posto")
    private Posto posto;
	
	private boolean habilitado;
	private boolean registroCompleto;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_perfil_usuario",
				joinColumns = @JoinColumn(name = "id_usuario"),
				inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private Set<Perfil> perfis = new HashSet<>();
	
	public Usuario() {
	}
	
	public Usuario(Long id, String nome, String sobrenome, String email, String senha, boolean habilitado) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.habilitado = habilitado;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Posto getPosto() {
		return posto;
	}

	public void setPosto(Posto posto) {
		this.posto = posto;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getAutorizacao())).collect(Collectors.toList());
	}
	
	@Override
	public String getPassword() {
		return senha;
	}
	
	@Override
	public String getUsername() {
		return identidade;
	}
	
	public boolean isRegistroCompleto() {
		return registroCompleto;
	}

	public void setRegistroCompleto(boolean registroCompleto) {
		this.registroCompleto = registroCompleto;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return habilitado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
}
