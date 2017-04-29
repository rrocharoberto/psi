package br.edu.univas.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the filaespera database table.
 * 
 */
@Entity
@Table(name="filaEspera")

@NamedQueries({
	@NamedQuery(name="FilaEspera.findAll", query="SELECT p FROM FilaEspera p order by p.nome asc")
})
public class FilaEspera implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#identifiers-derived-primarykeyjoincolumn
	@Id
	private long cpf;

	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	private Long telefone;

	private String encaminhamento;
	
	private String desistencia;
	
	public FilaEspera() {
	}
	
	public long getCpf() {
		return cpf;
	}
	
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getEncaminhamento() {
		return encaminhamento;
	}

	public void setEncaminhamento(String encaminhamento) {
		this.encaminhamento = encaminhamento;
	}

	public String getDesistencia() {
		return desistencia;
	}

	public void setDesistencia(String desistencia) {
		this.desistencia = desistencia;
	}

	@Override
	public String toString() {
		return "FilaEspera [cpf=" + cpf + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", telefone=" + telefone + ", encaminhamento=" + encaminhamento + ", desistencia=" + desistencia + "]";
	}

}