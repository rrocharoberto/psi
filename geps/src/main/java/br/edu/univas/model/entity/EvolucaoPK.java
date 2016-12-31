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

	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	private java.util.Date data;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer numeroprontuario;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, precision=131089)
	private long cpfestagiario;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer codigoservico;

	public EvolucaoPK() {
	}
	public java.util.Date getData() {
		return this.data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	public Integer getNumeroprontuario() {
		return this.numeroprontuario;
	}
	public void setNumeroprontuario(Integer numeroprontuario) {
		this.numeroprontuario = numeroprontuario;
	}
	public long getCpfestagiario() {
		return this.cpfestagiario;
	}
	public void setCpfestagiario(long cpfestagiario) {
		this.cpfestagiario = cpfestagiario;
	}
	public Integer getCodigoservico() {
		return this.codigoservico;
	}
	public void setCodigoservico(Integer codigoservico) {
		this.codigoservico = codigoservico;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EvolucaoPK)) {
			return false;
		}
		EvolucaoPK castOther = (EvolucaoPK)other;
		return 
			this.data.equals(castOther.data)
			&& this.numeroprontuario.equals(castOther.numeroprontuario)
			&& (this.cpfestagiario == castOther.cpfestagiario)
			&& this.codigoservico.equals(castOther.codigoservico);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.data.hashCode();
		hash = hash * prime + this.numeroprontuario.hashCode();
		hash = hash * prime + ((int) (this.cpfestagiario ^ (this.cpfestagiario >>> 32)));
		hash = hash * prime + this.codigoservico.hashCode();
		
		return hash;
	}
}