package br.com.sad2.capacitacao.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_avaliacao_treinamento")
public class AvaliacaoTreinamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * As avaliações se dividem em 5 notas:
	 * 1 - Insatisfatório
	 * 2 - Regular
	 * 3 - Bom
	 * 4 - Muito bom
	 * 5 - Excelente
	 */
	
	/**
	 * Avaliação do material didático
	 */
	private Integer qualidadeMaterial;
	private Integer apostilaObjetiva;
	private Integer apostilaAtualizada;
	
	/**
	 * Prova/Avaliação
	 */
	private Integer questoesRelacionadas;
	private Integer questoesClaras;
	
	/**
	 * Treinamento
	 * 
	 * avaliacaoGeralTreinamento é o único campo que vai de 0 a 10
	 */
	private Integer avaliacaoGeralTreinamento;
	private Integer abrangeuTodosObjetivos;
	
	/**
	 * Responsável pela consolidação da avaliação
	 */
	private String nomeResponsavel;
	private String funcaoResponsavel;
	
	@Lob
	private String comentariosSugestoes;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_treinamento", nullable = false, unique = true)
    private Treinamento treinamento;
	
	public AvaliacaoTreinamento() {
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

	public Treinamento getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
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

	public String getComentariosSugestoes() {
		return comentariosSugestoes;
	}

	public void setComentariosSugestoes(String comentariosSugestoes) {
		this.comentariosSugestoes = comentariosSugestoes;
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
		AvaliacaoTreinamento other = (AvaliacaoTreinamento) obj;
		return Objects.equals(id, other.id);
	}
}