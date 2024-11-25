package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

import br.com.sad2.capacitacao.entities.OM;

public class OMDTO implements Serializable {
	private static final long serialVersionUID = -8982124823660258036L;
	
	private Long id;
	private Long codigo;
	private String codom;
	private String sigla;
	private String rm;
	private String cma;
	private String de;
	private String cidadeestado;
	private String cnpj;
	private String tipo;
	private String bda;
	private String forpron;
	private String omcommaterial;
	private String endereco;
	private String cep;
	private Integer nivel;
	private Integer codregra;
	
	public OMDTO() {
	}

	public OMDTO(OM om) {
		this.id = om.getId();
	    this.codigo = om.getCodigo();
	    this.codom = om.getCodom();
	    this.sigla = om.getSigla();
	    this.rm = om.getRm();
	    this.cma = om.getCma();
	    this.de = om.getDe();
	    this.cidadeestado = om.getCidadeestado();
	    this.cnpj = om.getCnpj();
	    this.tipo = om.getTipo();
	    this.bda = om.getBda();
	    this.forpron = om.getForpron();
	    this.omcommaterial = om.getOmcommaterial();
	    this.endereco = om.getEndereco();
	    this.cep = om.getCep();
	    this.nivel = om.getNivel();
	    this.codregra = om.getCodregra();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCodom() {
		return codom;
	}

	public void setCodom(String codom) {
		this.codom = codom;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public String getCma() {
		return cma;
	}

	public void setCma(String cma) {
		this.cma = cma;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getCidadeestado() {
		return cidadeestado;
	}

	public void setCidadeestado(String cidadeestado) {
		this.cidadeestado = cidadeestado;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getBda() {
		return bda;
	}

	public void setBda(String bda) {
		this.bda = bda;
	}

	public String getForpron() {
		return forpron;
	}

	public void setForpron(String forpron) {
		this.forpron = forpron;
	}

	public String getOmcommaterial() {
		return omcommaterial;
	}

	public void setOmcommaterial(String omcommaterial) {
		this.omcommaterial = omcommaterial;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getCodregra() {
		return codregra;
	}

	public void setCodregra(Integer codregra) {
		this.codregra = codregra;
	}
}