package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the evolucao database table.
 * 
 */
@Embeddable
public class EvolucaoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer codigoservico;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=10)
	private String numeroprontuario;

	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	private java.util.Date data;

	public EvolucaoPK() {
	}
	public Integer getCodigoservico() {
		return this.codigoservico;
	}
	public void setCodigoservico(Integer codigoservico) {
		this.codigoservico = codigoservico;
	}
	public String getNumeroprontuario() {
		return this.numeroprontuario;
	}
	public void setNumeroprontuario(String numeroprontuario) {
		this.numeroprontuario = numeroprontuario;
	}
	public java.util.Date getData() {
		return this.data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoservico == null) ? 0 : codigoservico.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((numeroprontuario == null) ? 0 : numeroprontuario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EvolucaoPK other = (EvolucaoPK) obj;
		if (codigoservico == null) {
			if (other.codigoservico != null)
				return false;
		} else if (!codigoservico.equals(other.codigoservico))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (numeroprontuario == null) {
			if (other.numeroprontuario != null)
				return false;
		} else if (!numeroprontuario.equals(other.numeroprontuario))
			return false;
		return true;
	}

}