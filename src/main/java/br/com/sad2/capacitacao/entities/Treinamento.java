package br.com.sad2.capacitacao.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_treinamento")
public class Treinamento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sad;
    private String material;
    private String treinamento;
    private Integer tipo;
    private String subsistema;
    private Integer modalidade;
    private String brigada;
    //private String om;
    private Integer grupo;
    private Integer executor;
    private String instituicao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicio")
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fim")
    private Date dataFim;
    private Integer vagas;
    private Integer status;
    private Boolean avaliacaoPratica;
    private Boolean avaliacaoTeorica;
    private Boolean certificado;
    
    private Boolean nivelamento;
    private Integer cargaHoraria;
    private Integer publicoAlvo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_om", nullable = false)
    private OM om;
    
    @OneToMany(mappedBy = "treinamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instrutor> instrutores = new ArrayList<>();
    
    @OneToMany(mappedBy = "treinamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialDidaticoFile> materiaisDidaticos = new ArrayList<>();
    
    @OneToMany(mappedBy = "treinamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogisticaTreinamentoFile> logisticaTreinamentos = new ArrayList<>();
    
    @OneToMany(mappedBy = "treinamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turma> turmas = new ArrayList<>();
    
    @Lob
    private String descNivelamento;
    
    @Lob
    private String descricaoAtividade;
    
    @Lob
    private String observacoes;
    
    @Lob
    private String preRequisitos;
    
    @Lob
    private String logisticaTreinamento;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Treinamento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSad() {
		return sad;
	}

	public void setSad(String sad) {
		this.sad = sad;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(String treinamento) {
		this.treinamento = treinamento;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(String subsistema) {
		this.subsistema = subsistema;
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

	public OM getOm() {
		return om;
	}

	public void setOm(OM om) {
		this.om = om;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Integer getExecutor() {
		return executor;
	}

	public void setExecutor(Integer executor) {
		this.executor = executor;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
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

	public Integer getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Boolean getCertificado() {
		return certificado;
	}

	public void setCertificado(Boolean certificado) {
		this.certificado = certificado;
	}

	public String getLogisticaTreinamento() {
		return logisticaTreinamento;
	}

	public void setLogisticaTreinamento(String logisticaTreinamento) {
		this.logisticaTreinamento = logisticaTreinamento;
	}

	public Boolean getNivelamento() {
		return nivelamento;
	}

	public void setNivelamento(Boolean nivelamento) {
		this.nivelamento = nivelamento;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Integer getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(Integer publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(String preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public String getDescNivelamento() {
		return descNivelamento;
	}

	public void setDescNivelamento(String descNivelamento) {
		this.descNivelamento = descNivelamento;
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

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	public List<MaterialDidaticoFile> getMateriaisDidaticos() {
		return materiaisDidaticos;
	}

	public List<LogisticaTreinamentoFile> getLogisticaTreinamentos() {
		return logisticaTreinamentos;
	}

	public List<Turma> getTurmas() {
		return turmas;
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
		Treinamento other = (Treinamento) obj;
		return Objects.equals(id, other.id);
	}
}
