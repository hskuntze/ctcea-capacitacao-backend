package br.com.sad2.capacitacao.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_capacitado")
public class Capacitado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//Tipo = 1 - militar ou 2 - civil
	private Integer tipo;
	private String nomeCompleto;
	//Apenas para militares
	private String nomeGuerra;
	//Contato = email e celular
	private String email;
	private String celular;
	private String brigadaMilitar;
	//Instituição = Instituição para civil e OM do Militar para o militar
	private String instituicao;
	private Boolean avaliacaoPratica;
	private Boolean avaliacaoTeorica;
	private Boolean exigeNotaPratica;
	private Boolean exigeNotaTeorica;
	private String turma;
	private Float notaPratica;
	private Float notaTeorica;
	private Boolean certificado;
	private String tipoCertificado;
	private String numeroBi;
	private String funcao;
	
	@Lob
	private String observacoesAvaliacaoPratica;
	
	@Lob
	private String observacoesAvaliacaoTeorica;

	@ManyToOne
	@JoinColumn(name = "id_treinamento")
	private Treinamento treinamento;
	
	@ManyToOne
    @JoinColumn(name = "id_posto", nullable = false)
    private Posto posto;

	public Capacitado() {
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

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public Posto getPosto() {
		return posto;
	}

	public void setPosto(Posto posto) {
		this.posto = posto;
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

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
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

	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
	}

	public Treinamento getTreinamento() {
		return treinamento;
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
		Capacitado other = (Capacitado) obj;
		return Objects.equals(id, other.id);
	}
}