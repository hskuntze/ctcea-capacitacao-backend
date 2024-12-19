package br.com.sad2.capacitacao.dto.relatorios;

import java.util.Objects;

/**
 * Classe que tem a finalidade de realizar a transferência dos dados necessários 
 * para gerar os gráficos de relatório "Treinamentos por OM"
 */
public class TreinamentoOmDTO {

	public String siglaOM;
	public Long quantidade;
	
	public TreinamentoOmDTO() {
	}

	public TreinamentoOmDTO(String siglaOM, Long quantidade) {
		this.siglaOM = siglaOM;
		this.quantidade = quantidade;
	}

	public String getSiglaOM() {
		return siglaOM;
	}

	public void setSiglaOM(String siglaOM) {
		this.siglaOM = siglaOM;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "TreinamentoStatusDTO [siglaOM=" + siglaOM + ", quantidade=" + quantidade + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(quantidade, siglaOM);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreinamentoOmDTO other = (TreinamentoOmDTO) obj;
		return Objects.equals(quantidade, other.quantidade) && Objects.equals(siglaOM, other.siglaOM);
	}
}
