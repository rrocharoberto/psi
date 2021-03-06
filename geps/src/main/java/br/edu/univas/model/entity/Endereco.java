package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@Table(name="endereco")
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=20)
	private String numeroProntuario;

	@Column(nullable=false, length=50)
	private String bairro;

	private Integer cep;

	@Column(length=10)
	private String complemento;

	@Column(nullable=false, length=50)
	private String logradouro;

	@Column(nullable=false, length=50)
	private String municipio;

	private Integer numero;

	@Column(nullable=false, length=100)
	private String rua;

	@Column(nullable=false, length=20)
	private String tipoEndereco;

	@Column(nullable=false, length=30)
	private String uf;

	public Endereco() {
	}

	public String getNumeroprontuario() {
		return this.numeroProntuario;
	}

	public void setNumeroprontuario(String numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCep() {
		return this.cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getTipoEndereco() {
		return this.tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}