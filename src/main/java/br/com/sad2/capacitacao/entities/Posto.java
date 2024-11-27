package br.com.sad2.capacitacao.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_posto")
public class Posto {

	@Id
	private Long id;
	private String titulo;
	
	@OneToMany(mappedBy = "posto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Capacitado> capacitados = new ArrayList<>();
	
	public Posto() {
	}

	public Posto(Long id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Capacitado> getCapacitados() {
		return capacitados;
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
		Posto other = (Posto) obj;
		return Objects.equals(id, other.id);
	}
}