package br.com.sad2.capacitacao.entities;

import java.util.Objects;

import br.com.sad2.capacitacao.dto.TreinamentoFiltroDTO;

public class TreinamentoCapacitadoFiltro {

	private TreinamentoFiltroDTO treinamento;
	private String nomesCompletos;

	public TreinamentoCapacitadoFiltro() {
	}
	
	public TreinamentoCapacitadoFiltro(Treinamento treinamento, String nomesCompletos) {
		this.treinamento = new TreinamentoFiltroDTO(treinamento);
		this.nomesCompletos = nomesCompletos;
	}

	public TreinamentoFiltroDTO getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(TreinamentoFiltroDTO treinamento) {
		this.treinamento = treinamento;
	}

	public String getNomesCompletos() {
		return nomesCompletos;
	}

	public void setNomesCompletos(String nomesCompletos) {
		this.nomesCompletos = nomesCompletos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(treinamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreinamentoCapacitadoFiltro other = (TreinamentoCapacitadoFiltro) obj;
		return Objects.equals(treinamento, other.treinamento);
	}
}