package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the filaespera database table.
 * 
 */
@Entity
@Table(name="filaespera")

@NamedQueries({
	@NamedQuery(name="FilaEspera.findAll", query="SELECT p FROM FilaEspera p order by p.dataCadastro")
})

public class FilaEspera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=131089)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dataNascimento;

	@Column(length=200)
	private String desistencia;

	@Column(length=200)
	private String encaminhamento;

	@Column(nullable=false, length=50)
	private String nome;

	@Column(precision=131089)
	private Long telefone;

	public FilaEspera() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDesistencia() {
		return this.desistencia;
	}

	public void setDesistencia(String desistencia) {
		this.desistencia = desistencia;
	}

	public String getEncaminhamento() {
		return this.encaminhamento;
	}

	public void setEncaminhamento(String encaminhamento) {
		this.encaminhamento = encaminhamento;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTelefone() {
		return this.telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

}