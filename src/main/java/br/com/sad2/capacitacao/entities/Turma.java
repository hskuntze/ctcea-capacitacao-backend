package br.com.sad2.capacitacao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A classe "Turma" depende da existência de um "Treinamento".
 * <p>
 * Turmas não podem ser cadastradas a não ser durante o registro
 * de um Treinamento. Dessa forma, cria-se uma dependência entre essas classes.
 * (Comportamento desejado)
 */
@Entity
@Table(name = "tb_turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_treinamento")
	private Treinamento treinamento;
	
	public Turma() {
	}
	
	public Turma(String nome) {
		this.nome = nome;
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

	public Treinamento getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
	}
}