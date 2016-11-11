package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cpf;

	private String bairro;

	private Integer cep;

	private String complemento;

	private String logradouro;

	private String municipio;

	private Integer numero;

	private String rua;

	private String tipoendereco;

	private String uf;

	//bi-directional one-to-one association to Dadospessoai
	@OneToOne
	@JoinColumn(name="cpf")
	private Dadospessoai dadospessoais;

	public Endereco() {
	}

	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
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

	public String getTipoendereco() {
		return this.tipoendereco;
	}

	public void setTipoendereco(String tipoendereco) {
		this.tipoendereco = tipoendereco;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Dadospessoai getDadospessoais() {
		return this.dadospessoais;
	}

	public void setDadospessoais(Dadospessoai dadospessoais) {
		this.dadospessoais = dadospessoais;
	}

}