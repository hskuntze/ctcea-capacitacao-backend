package br.com.sad2.capacitacao.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_ocorrencia")
public class Ocorrencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@Lob
	private String descricaoOcorrencia;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "data_ocorrencia")
	private Date dataOcorrencia;
	
	//1 - Logística; 2 - Técnica; 3 - Organizacional; 4 - Didática; 5 - Outros
	private Integer tipoOcorrencia;
	@Lob
	private String descricaoTipoOutros;
	private Boolean impactoOcorrencia;
	private Integer nivelImpacto;
	@Lob
	private String descricaoImpacto;
	//1 - Solucionado; 2 - Em análise; 3 - Pendente; 4 - Não se aplica
	private Integer statusClassificacao;
	
	//Se statusClassificacao = 1
	private String solucaoAdotada;
	@Temporal(TemporalType.DATE)
    @Column(name = "data_solucao")
	private Date dataSolucao;
	private String nomeResponsavelSolucao;
	private String contatoResponsavelSolucao;
	private String instituicaoResponsavelSolucao;
	
	//Se statusClassificacao = 2 ou 3
	private String descricaoClassificacao;
	
	//1 - Baixa; 2 - Média; 3 - Alta
	private Integer probabilidadeRecorrencia;
	
	@Lob
	private String descricaoLicoesAprendidas;
	
	private String nomeResponsavelOcorrencia;
	private String contatoResponsavelOcorrencia;
	private String instituicaoResponsavelOcorrencia;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "data_registro")
	private Date dataRegistro;
	
	@Lob
	private String observacoesGerais;
	
	@ManyToOne
	@JoinColumn(name = "id_treinamento")
	private Treinamento treinamento;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Ocorrencia() {
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

	public Treinamento getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
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
		Ocorrencia other = (Ocorrencia) obj;
		return Objects.equals(id, other.id);
	}
}