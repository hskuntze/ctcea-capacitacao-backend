package br.com.sad2.capacitacao.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import br.com.sad2.capacitacao.entities.Capacitado;

public class CapacitadoDTO implements Serializable {
	private static final long serialVersionUID = 5192613100520253082L;

	private Long id;
	private Integer modalidade;
	private String brigada;
	private String om;
	private Integer grupo;
	private String subsistema;
	private String posto;
	private String nomeCompleto;
	private String nomeGuerra;
	private String email;
	private String celular;
	private String brigadaMilitar;
	private String omMilitar;
	private Boolean avaliacaoPratica;
	private Boolean avaliacaoTeorica;
	private Float notaPratica;
	private Float notaTeorica;
	private Boolean certificado;
	private String tipoCertificado;
	private String numeroBi;
	private String observacoes;
	private Date dataInicio;
	private Date dataFim;
	
	private Set<TreinamentoDTO> treinamentos = new HashSet<>();

	public CapacitadoDTO() {
	}
	
	public CapacitadoDTO(Capacitado capacitado) {
	    this.id = capacitado.getId();
	    this.modalidade = capacitado.getModalidade();
	    this.brigada = capacitado.getBrigada();
	    this.om = capacitado.getOm();
	    this.grupo = capacitado.getGrupo();
	    this.subsistema = capacitado.getSubsistema();
	    this.posto = capacitado.getPosto();
	    this.nomeCompleto = capacitado.getNomeCompleto();
	    this.nomeGuerra = capacitado.getNomeGuerra();
	    this.email = capacitado.getEmail();
	    this.celular = capacitado.getCelular();
	    this.brigadaMilitar = capacitado.getBrigadaMilitar();
	    this.omMilitar = capacitado.getOmMilitar();
	    this.avaliacaoPratica = capacitado.getAvaliacaoPratica();
	    this.avaliacaoTeorica = capacitado.getAvaliacaoTeorica();
	    this.notaPratica = capacitado.getNotaPratica();
	    this.notaTeorica = capacitado.getNotaTeorica();
	    this.certificado = capacitado.getCertificado();
	    this.tipoCertificado = capacitado.getTipoCertificado();
	    this.numeroBi = capacitado.getNumeroBi();
	    this.observacoes = capacitado.getObservacoes();
	    this.dataInicio = capacitado.getDataInicio();
	    this.dataFim = capacitado.getDataFim();
	    
	    treinamentos.clear();
	    capacitado.getTreinamentos().forEach(t -> {
	    	this.treinamentos.add(new TreinamentoDTO(t));
	    });
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getModalidade() {
		return modalidade;
	}

	public void setModalidade(Integer modalidade) {
		this.modalidade = modalidade;
	}

	public String getBrigada() {
		return brigada;
	}

	public void setBrigada(String brigada) {
		this.brigada = brigada;
	}

	public String getOm() {
		return om;
	}

	public void setOm(String om) {
		this.om = om;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public String getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(String subsistema) {
		this.subsistema = subsistema;
	}

	public String getPosto() {
		return posto;
	}

	public void setPosto(String posto) {
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

	public String getBrigadaMilitar() {
		return brigadaMilitar;
	}

	public void setBrigadaMilitar(String brigadaMilitar) {
		this.brigadaMilitar = brigadaMilitar;
	}

	public String getOmMilitar() {
		return omMilitar;
	}

	public void setOmMilitar(String omMilitar) {
		this.omMilitar = omMilitar;
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

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Set<TreinamentoDTO> getTreinamentos() {
		return treinamentos;
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