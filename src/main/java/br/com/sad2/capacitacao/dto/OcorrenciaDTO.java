package br.com.sad2.capacitacao.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.sad2.capacitacao.entities.Ocorrencia;

public class OcorrenciaDTO implements Serializable {
	private static final long serialVersionUID = 1547072686695750164L;

	private Long id;
	private String titulo;
	private String descricaoOcorrencia;
	private Date dataOcorrencia;
	private Integer tipoOcorrencia;
	private String descricaoTipoOutros;
	private Boolean impactoOcorrencia;
	private Integer nivelImpacto;
	private String descricaoImpacto;
	private Integer statusClassificacao;
	private String solucaoAdotada;
	private Date dataSolucao;
	private String nomeResponsavelSolucao;
	private String contatoResponsavelSolucao;
	private String instituicaoResponsavelSolucao;
	private String descricaoClassificacao;
	private Integer probabilidadeRecorrencia;
	private String descricaoLicoesAprendidas;
	private String nomeResponsavelOcorrencia;
	private String contatoResponsavelOcorrencia;
	private String instituicaoResponsavelOcorrencia;
	private Date dataRegistro;
	private String observacoesGerais;
	private TreinamentoBasicoDTO treinamento;

	public OcorrenciaDTO() {
	}

	public OcorrenciaDTO(Ocorrencia o) {
		this.id = o.getId();
		this.titulo = o.getTitulo();
		this.descricaoOcorrencia = o.getDescricaoOcorrencia();
		this.dataOcorrencia = o.getDataOcorrencia();
		this.tipoOcorrencia = o.getTipoOcorrencia();
		this.descricaoTipoOutros = o.getDescricaoTipoOutros();
		this.impactoOcorrencia = o.getImpactoOcorrencia();
		this.nivelImpacto = o.getNivelImpacto();
		this.descricaoImpacto = o.getDescricaoImpacto();
		this.statusClassificacao = o.getStatusClassificacao();
		this.solucaoAdotada = o.getSolucaoAdotada();
		this.dataSolucao = o.getDataSolucao();
		this.nomeResponsavelSolucao = o.getNomeResponsavelSolucao();
		this.contatoResponsavelSolucao = o.getContatoResponsavelSolucao();
		this.instituicaoResponsavelSolucao = o.getInstituicaoResponsavelSolucao();
		this.descricaoClassificacao = o.getDescricaoClassificacao();
		this.probabilidadeRecorrencia = o.getProbabilidadeRecorrencia();
		this.descricaoLicoesAprendidas = o.getDescricaoLicoesAprendidas();
		this.nomeResponsavelOcorrencia = o.getNomeResponsavelOcorrencia();
		this.contatoResponsavelOcorrencia = o.getContatoResponsavelOcorrencia();
		this.instituicaoResponsavelOcorrencia = o.getInstituicaoResponsavelOcorrencia();
		this.dataRegistro = o.getDataRegistro();
		this.observacoesGerais = o.getObservacoesGerais();
		
		this.treinamento = new TreinamentoBasicoDTO(o.getTreinamento());
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

	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}

	public void setDescricaoOcorrencia(String descricaoOcorrencia) {
		this.descricaoOcorrencia = descricaoOcorrencia;
	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public Integer getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(Integer tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public String getDescricaoTipoOutros() {
		return descricaoTipoOutros;
	}

	public void setDescricaoTipoOutros(String descricaoTipoOutros) {
		this.descricaoTipoOutros = descricaoTipoOutros;
	}

	public Boolean getImpactoOcorrencia() {
		return impactoOcorrencia;
	}

	public void setImpactoOcorrencia(Boolean impactoOcorrencia) {
		this.impactoOcorrencia = impactoOcorrencia;
	}

	public Integer getNivelImpacto() {
		return nivelImpacto;
	}

	public void setNivelImpacto(Integer nivelImpacto) {
		this.nivelImpacto = nivelImpacto;
	}

	public String getDescricaoImpacto() {
		return descricaoImpacto;
	}

	public void setDescricaoImpacto(String descricaoImpacto) {
		this.descricaoImpacto = descricaoImpacto;
	}

	public Integer getStatusClassificacao() {
		return statusClassificacao;
	}

	public void setStatusClassificacao(Integer statusClassificacao) {
		this.statusClassificacao = statusClassificacao;
	}

	public String getSolucaoAdotada() {
		return solucaoAdotada;
	}

	public void setSolucaoAdotada(String solucaoAdotada) {
		this.solucaoAdotada = solucaoAdotada;
	}

	public Date getDataSolucao() {
		return dataSolucao;
	}

	public void setDataSolucao(Date dataSolucao) {
		this.dataSolucao = dataSolucao;
	}

	public String getNomeResponsavelSolucao() {
		return nomeResponsavelSolucao;
	}

	public void setNomeResponsavelSolucao(String nomeResponsavelSolucao) {
		this.nomeResponsavelSolucao = nomeResponsavelSolucao;
	}

	public String getContatoResponsavelSolucao() {
		return contatoResponsavelSolucao;
	}

	public void setContatoResponsavelSolucao(String contatoResponsavelSolucao) {
		this.contatoResponsavelSolucao = contatoResponsavelSolucao;
	}

	public String getInstituicaoResponsavelSolucao() {
		return instituicaoResponsavelSolucao;
	}

	public void setInstituicaoResponsavelSolucao(String instituicaoResponsavelSolucao) {
		this.instituicaoResponsavelSolucao = instituicaoResponsavelSolucao;
	}

	public String getDescricaoClassificacao() {
		return descricaoClassificacao;
	}

	public void setDescricaoClassificacao(String descricaoClassificacao) {
		this.descricaoClassificacao = descricaoClassificacao;
	}

	public Integer getProbabilidadeRecorrencia() {
		return probabilidadeRecorrencia;
	}

	public void setProbabilidadeRecorrencia(Integer probabilidadeRecorrencia) {
		this.probabilidadeRecorrencia = probabilidadeRecorrencia;
	}

	public String getDescricaoLicoesAprendidas() {
		return descricaoLicoesAprendidas;
	}

	public void setDescricaoLicoesAprendidas(String descricaoLicoesAprendidas) {
		this.descricaoLicoesAprendidas = descricaoLicoesAprendidas;
	}

	public String getNomeResponsavelOcorrencia() {
		return nomeResponsavelOcorrencia;
	}

	public void setNomeResponsavelOcorrencia(String nomeResponsavelOcorrencia) {
		this.nomeResponsavelOcorrencia = nomeResponsavelOcorrencia;
	}

	public String getContatoResponsavelOcorrencia() {
		return contatoResponsavelOcorrencia;
	}

	public void setContatoResponsavelOcorrencia(String contatoResponsavelOcorrencia) {
		this.contatoResponsavelOcorrencia = contatoResponsavelOcorrencia;
	}

	public String getInstituicaoResponsavelOcorrencia() {
		return instituicaoResponsavelOcorrencia;
	}

	public void setInstituicaoResponsavelOcorrencia(String instituicaoResponsavelOcorrencia) {
		this.instituicaoResponsavelOcorrencia = instituicaoResponsavelOcorrencia;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getObservacoesGerais() {
		return observacoesGerais;
	}

	public void setObservacoesGerais(String observacoesGerais) {
		this.observacoesGerais = observacoesGerais;
	}

	public TreinamentoBasicoDTO getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(TreinamentoBasicoDTO treinamento) {
		this.treinamento = treinamento;
	}
}