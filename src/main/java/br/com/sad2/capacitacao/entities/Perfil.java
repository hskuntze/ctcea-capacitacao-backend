package br.com.sad2.capacitacao.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Perfis dos "Usuarios" do sistema.
 * <p>
 * PERFIS DEFINIDOS:<br>
 * - PERFIL_ADMIN: Todos os privilégios<br>
 * - PERFIL_USUARIO: Todos os módulos, com exceção do módulo de controle de usuário (endpoints de usuario)
 */
@Entity
@Table(name = "tb_perfil")
public class Perfil implements Serializable {
	private static final long serialVersionUID = -7359631538749315449L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String autorizacao;
	
	public Perfil() {
	}

	public Perfil(Long id, String autorizacao) {
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
		Perfil other = (Perfil) obj;
		return Objects.equals(id, other.id);
	}
}
