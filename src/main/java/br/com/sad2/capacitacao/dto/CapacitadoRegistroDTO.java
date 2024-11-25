package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

import br.com.sad2.capacitacao.entities.Capacitado;

public class CapacitadoRegistroDTO extends CapacitadoDTO implements Serializable {
	private static final long serialVersionUID = 1783136966212744830L;
	
	private Long idTreinamento;

	public CapacitadoRegistroDTO() {
	}
	
	public CapacitadoRegistroDTO(Capacitado capacitado) {
	    super(capacitado);
	}

	public Long getIdTreinamento() {
		return idTreinamento;
	}

	public void setIdTreinamento(Long idTreinamento) {
		this.idTreinamento = idTreinamento;
	}
}