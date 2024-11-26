package br.com.sad2.capacitacao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_instrutor")
public class Instrutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String contato;
	
	@ManyToOne
	@JoinColumn(name = "treinamento_id")
	private Treinamento treinamento;
	
	public Instrutor() {
	}

	public Instrutor(String nome, String email, String contato) {
		this.nome = nome;
		this.email = email;
		this.contato = contato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Treinamento getTreinamento() {
		return treinamento;
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

	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
	}

	@Override
	public String toString() {
		return "Instrutor [id=" + id + ", nome=" + nome + ", email=" + email + ", contato=" + contato + ", treinamento="
				+ treinamento + "]";
	}
}