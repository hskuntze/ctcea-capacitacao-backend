package br.com.sad2.capacitacao.dto;

import java.io.Serializable;
import java.util.Objects;

import br.com.sad2.capacitacao.entities.Capacitado;

public class CapacitadoDTO implements Serializable {
	private static final long serialVersionUID = 5192613100520253082L;

	private Long id;
	private Integer tipo;
	private String nomeCompleto;
	private String nomeGuerra;
	private String email;
	private String celular;
	private String brigadaMilitar;
	private String instituicao;
	private Boolean avaliacaoPratica;
	private Boolean avaliacaoTeorica;
	private Boolean exigeNotaPratica;
	private Boolean exigeNotaTeorica;
	private Float notaPratica;
	private Float notaTeorica;
	private String turma;
	private Boolean certificado;
	private String tipoCertificado;
	private String numeroBi;
	private String observacoesAvaliacaoPratica;
	private String observacoesAvaliacaoTeorica;
	private String funcao;
	
	private TreinamentoBasicoDTO treinamento;
	private PostoDTO posto;
	
	public CapacitadoDTO() {
	}
	
	public CapacitadoDTO(Capacitado capacitado) {
	    this.id = capacitado.getId();
	    this.tipo = capacitado.getTipo();
	    this.posto = new PostoDTO(capacitado.getPosto());
	    this.nomeCompleto = capacitado.getNomeCompleto();
	    this.nomeGuerra = capacitado.getNomeGuerra();
	    this.email = capacitado.getEmail();
	    this.celular = capacitado.getCelular();
	    this.brigadaMilitar = capacitado.getBrigadaMilitar();
	    this.instituicao = capacitado.getInstituicao();
	    this.avaliacaoPratica = capacitado.getAvaliacaoPratica();
	    this.avaliacaoTeorica = capacitado.getAvaliacaoTeorica();
	    this.exigeNotaPratica = capacitado.getExigeNotaPratica();
	    this.exigeNotaTeorica = capacitado.getExigeNotaTeorica();
	    this.notaPratica = capacitado.getNotaPratica();
	    this.notaTeorica = capacitado.getNotaTeorica();
	    this.turma = capacitado.getTurma();
	    this.certificado = capacitado.getCertificado();
	    this.tipoCertificado = capacitado.getTipoCertificado();
	    this.numeroBi = capacitado.getNumeroBi();
	    this.observacoesAvaliacaoPratica = capacitado.getObservacoesAvaliacaoPratica();
	    this.observacoesAvaliacaoTeorica = capacitado.getObservacoesAvaliacaoTeorica();
	    this.treinamento = new TreinamentoBasicoDTO(capacitado.getTreinamento());
	    this.funcao = capacitado.getFuncao();
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

	public PostoDTO getPosto() {
		return posto;
	}

	public void setPosto(PostoDTO posto) {
		this.posto = posto;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getBrigadaMilitar() {
		return brigadaMilitar;
	}

	public void setBrigadaMilitar(String brigadaMilitar) {
		this.brigadaMilitar = brigadaMilitar;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Boolean getAvaliacaoPratica() {
		return avaliacaoPratica;
	}

	public void setAvaliacaoPratica(Boolean avaliacaoPratica) {
		this.avaliacaoPratica = avaliacaoPratica;
	}

	public Boolean getAvaliacaoTeorica() {
		return avaliacaoTeorica;
	}

	public void setAvaliacaoTeorica(Boolean avaliacaoTeorica) {
		this.avaliacaoTeorica = avaliacaoTeorica;
	}
	
	public Boolean getExigeNotaPratica() {
		return exigeNotaPratica;
	}

	public void setExigeNotaPratica(Boolean exijeNotaPratica) {
		this.exigeNotaPratica = exijeNotaPratica;
	}

	public Boolean getExigeNotaTeorica() {
		return exigeNotaTeorica;
	}

	public void setExigeNotaTeorica(Boolean exijeNotaTeorica) {
		this.exigeNotaTeorica = exijeNotaTeorica;
	}

	public Float getNotaPratica() {
		return notaPratica;
	}

	public void setNotaPratica(Float notaPratica) {
		this.notaPratica = notaPratica;
	}

	public Float getNotaTeorica() {
		return notaTeorica;
	}

	public void setNotaTeorica(Float notaTeorica) {
		this.notaTeorica = notaTeorica;
	}

	public Boolean getCertificado() {
		return certificado;
	}

	public void setCertificado(Boolean certificado) {
		this.certificado = certificado;
	}

	public String getTipoCertificado() {
		return tipoCertificado;
	}

	public void setTipoCertificado(String tipoCertificado) {
		this.tipoCertificado = tipoCertificado;
	}

	public String getNumeroBi() {
		return numeroBi;
	}

	public void setNumeroBi(String numeroBi) {
		this.numeroBi = numeroBi;
	}
	
	public String getObservacoesAvaliacaoPratica() {
		return observacoesAvaliacaoPratica;
	}

	public void setObservacoesAvaliacaoPratica(String observacoesAvaliacaoPratica) {
		this.observacoesAvaliacaoPratica = observacoesAvaliacaoPratica;
	}

	public String getObservacoesAvaliacaoTeorica() {
		return observacoesAvaliacaoTeorica;
	}

	public void setObservacoesAvaliacaoTeorica(String observacoesAvaliacaoTeorica) {
		this.observacoesAvaliacaoTeorica = observacoesAvaliacaoTeorica;
	}

	public TreinamentoBasicoDTO getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(TreinamentoBasicoDTO treinamento) {
		this.treinamento = treinamento;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
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
		CapacitadoDTO other = (CapacitadoDTO) obj;
		return Objects.equals(id, other.id);
	}
}