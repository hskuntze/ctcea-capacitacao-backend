package br.com.sad2.capacitacao.dto.relatorios;

import java.util.Objects;

/**
 * Classe que tem a finalidade de realizar a transferência dos dados necessários 
 * para gerar os gráficos de relatório "Treinamentos por Status"
 */
public class TreinamentoStatusDTO {

	public String descStatus;
	public Long quantidade;
	
	public TreinamentoStatusDTO() {
	}

	public TreinamentoStatusDTO(String descStatus, Long quantidade) {
		this.descStatus = descStatus;
		this.quantidade = quantidade;
	}

	public String getDescStatus() {
		return descStatus;
	}

	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "TreinamentoStatusDTO [descStatus=" + descStatus + ", quantidade=" + quantidade + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(descStatus, quantidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreinamentoStatusDTO other = (TreinamentoStatusDTO) obj;
		return Objects.equals(descStatus, other.descStatus) && Objects.equals(quantidade, other.quantidade);
	}
}
