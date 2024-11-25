package br.com.sad2.capacitacao.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.sad2.capacitacao.entities.Treinamento;

public class TreinamentoDTO implements Serializable {
	private static final long serialVersionUID = -2265131824545407728L;

	private Long id;
	private String sad;
	private String material;
	private String treinamento;
	private Integer tipo;
	private String subsistema;
	private Integer modalidade;
	private String brigada;
	private String om;
	private Integer grupo;
	private Integer executor;
	private String instituicao;
	private Date dataInicio;
	private Date dataFim;
	private Integer vagas;
	private Integer status;
	private Boolean avaliacaoPratica;
	private Boolean avaliacaoTeorica;
	private String nomeInstrutores;
	private String contatoInstrutores;
	private Boolean certificado;
	private String logisticaTreinamento;
	private Boolean nivelamento;
	private Integer cargaHoraria;
	private Integer publicoAlvo;
	private String descricaoAtividade;
	private String materialDidatico;
	private String observacoes;
	private String preRequisitos;
	
	public TreinamentoDTO() {
	}
	
	public TreinamentoDTO(Treinamento t) {
        this.id = t.getId();
        this.sad = t.getSad();
        this.material = t.getMaterial();
        this.treinamento = t.getTreinamento();
        this.tipo = t.getTipo();
        this.subsistema = t.getSubsistema();
        this.modalidade = t.getModalidade();
        this.brigada = t.getBrigada();
        this.om = t.getOm();
        this.grupo = t.getGrupo();
        this.executor = t.getExecutor();
        this.instituicao = t.getInstituicao();
        this.dataInicio = t.getDataInicio();
        this.dataFim = t.getDataFim();
        this.vagas = t.getVagas();
        this.status = t.getStatus();
        this.avaliacaoPratica = t.getAvaliacaoPratica();
        this.avaliacaoTeorica = t.getAvaliacaoTeorica();
        this.nomeInstrutores = t.getNomeInstrutores();
        this.contatoInstrutores = t.getContatoInstrutores();
        this.certificado = t.getCertificado();
        this.logisticaTreinamento = t.getLogisticaTreinamento();
        this.nivelamento = t.getNivelamento();
        this.cargaHoraria = t.getCargaHoraria();
        this.publicoAlvo = t.getPublicoAlvo();
        this.descricaoAtividade = t.getDescricaoAtividade();
        this.materialDidatico = t.getMaterialDidatico();
        this.observacoes = t.getObservacoes();
        this.preRequisitos = t.getPreRequisitos();
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

	public String getNomeInstrutores() {
		return nomeInstrutores;
	}

	public void setNomeInstrutores(String nomeInstrutores) {
		this.nomeInstrutores = nomeInstrutores;
	}

	public String getContatoInstrutores() {
		return contatoInstrutores;
	}

	public void setContatoInstrutores(String contatoInstrutores) {
		this.contatoInstrutores = contatoInstrutores;
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

	public String getMaterialDidatico() {
		return materialDidatico;
	}

	public void setMaterialDidatico(String materialDidatico) {
		this.materialDidatico = materialDidatico;
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
}
