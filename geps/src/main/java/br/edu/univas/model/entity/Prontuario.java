package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the prontuario database table.
 * 
 */
@Entity
@NamedQuery(name="Prontuario.findAll", query="SELECT p FROM Prontuario p")
public class Prontuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer numero;

	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date datafechamento;

	private String declaracao;

	private String motivofechamento;

	private String termoconsentimento;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="cpf")
	private Paciente paciente;

	public Prontuario() {
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getDatafechamento() {
		return this.datafechamento;
	}

	public void setDatafechamento(Date datafechamento) {
		this.datafechamento = datafechamento;
	}

	public String getDeclaracao() {
		return this.declaracao;
	}

	public void setDeclaracao(String declaracao) {
		this.declaracao = declaracao;
	}

	public String getMotivofechamento() {
		return this.motivofechamento;
	}

	public void setMotivofechamento(String motivofechamento) {
		this.motivofechamento = motivofechamento;
	}

	public String getTermoconsentimento() {
		return this.termoconsentimento;
	}

	public void setTermoconsentimento(String termoconsentimento) {
		this.termoconsentimento = termoconsentimento;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}