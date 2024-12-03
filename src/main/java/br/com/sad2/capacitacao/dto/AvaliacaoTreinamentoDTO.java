package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

import br.com.sad2.capacitacao.entities.AvaliacaoTreinamento;

public class AvaliacaoTreinamentoDTO implements Serializable {
	private static final long serialVersionUID = 2839514436775311818L;
	
	private Long id;
	private Integer qualidadeMaterial;
	private Integer apostilaObjetiva;
	private Integer apostilaAtualizada;
	private Integer questoesRelacionadas;
	private Integer questoesClaras;
	private Integer avaliacaoGeralTreinamento;
	private Integer abrangeuTodosObjetivos;
	private String nomeResponsavel;
	private String funcaoResponsavel;
	private String comentariosSugestoes;
    private TreinamentoDTO treinamento;
    
    public AvaliacaoTreinamentoDTO() {
	}
 
    public AvaliacaoTreinamentoDTO(AvaliacaoTreinamento a) {
    	this.id = a.getId();
    	this.qualidadeMaterial = a.getQualidadeMaterial();
    	this.apostilaObjetiva = a.getApostilaObjetiva();
    	this.apostilaAtualizada = a.getApostilaAtualizada();
    	this.questoesRelacionadas = a.getQuestoesRelacionadas();
    	this.questoesClaras = a.getQuestoesClaras();
    	this.avaliacaoGeralTreinamento = a.getAvaliacaoGeralTreinamento();
    	this.abrangeuTodosObjetivos = a.getAbrangeuTodosObjetivos();
    	this.nomeResponsavel = a.getNomeResponsavel();
    	this.funcaoResponsavel = a.getFuncaoResponsavel();
    	this.comentariosSugestoes = a.getComentariosSugestoes();
    	
    	this.treinamento = new TreinamentoDTO(a.getTreinamento());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQualidadeMaterial() {
		return qualidadeMaterial;
	}

	public void setQualidadeMaterial(Integer qualidadeMaterial) {
		this.qualidadeMaterial = qualidadeMaterial;
	}

	public Integer getApostilaObjetiva() {
		return apostilaObjetiva;
	}

	public void setApostilaObjetiva(Integer apostilaObjetiva) {
		this.apostilaObjetiva = apostilaObjetiva;
	}

	public Integer getApostilaAtualizada() {
		return apostilaAtualizada;
	}

	public void setApostilaAtualizada(Integer apostilaAtualizada) {
		this.apostilaAtualizada = apostilaAtualizada;
	}

	public Integer getQuestoesRelacionadas() {
		return questoesRelacionadas;
	}

	public void setQuestoesRelacionadas(Integer questoesRelacionadas) {
		this.questoesRelacionadas = questoesRelacionadas;
	}

	public Integer getQuestoesClaras() {
		return questoesClaras;
	}

	public void setQuestoesClaras(Integer questoesClaras) {
		this.questoesClaras = questoesClaras;
	}

	public Integer getAvaliacaoGeralTreinamento() {
		return avaliacaoGeralTreinamento;
	}

	public void setAvaliacaoGeralTreinamento(Integer avaliacaoGeralTreinamento) {
		this.avaliacaoGeralTreinamento = avaliacaoGeralTreinamento;
	}

	public Integer getAbrangeuTodosObjetivos() {
		return abrangeuTodosObjetivos;
	}

	public void setAbrangeuTodosObjetivos(Integer abrangeuTodosObjetivos) {
		this.abrangeuTodosObjetivos = abrangeuTodosObjetivos;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getFuncaoResponsavel() {
		return funcaoResponsavel;
	}

	public void setFuncaoResponsavel(String funcaoResponsavel) {
		this.funcaoResponsavel = funcaoResponsavel;
	}

	public TreinamentoDTO getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(TreinamentoDTO treinamento) {
		this.treinamento = treinamento;
	}

	public String getComentariosSugestoes() {
		return comentariosSugestoes;
	}

	public void setComentariosSugestoes(String comentariosSugestoes) {
		this.comentariosSugestoes = comentariosSugestoes;
	}
}