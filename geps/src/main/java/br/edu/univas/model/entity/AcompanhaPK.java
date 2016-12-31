package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the acompanha database table.
 * 
 */
@Embeddable
public class AcompanhaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer numeroprontuario;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, precision=131089)
	private long cpf;

	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	private java.util.Date datainicio;

	public AcompanhaPK() {
	}
	public Integer getNumeroprontuario() {
		return this.numeroprontuario;
	}
	public void setNumeroprontuario(Integer numeroprontuario) {
		this.numeroprontuario = numeroprontuario;
	}
	public long getCpf() {
		return this.cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public java.util.Date getDatainicio() {
		return this.datainicio;
	}
	public void setDatainicio(java.util.Date datainicio) {
		this.datainicio = datainicio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AcompanhaPK)) {
			return false;
		}
		AcompanhaPK castOther = (AcompanhaPK)other;
		return 
			this.numeroprontuario.equals(castOther.numeroprontuario)
			&& (this.cpf == castOther.cpf)
			&& this.datainicio.equals(castOther.datainicio);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numeroprontuario.hashCode();
		hash = hash * prime + ((int) (this.cpf ^ (this.cpf >>> 32)));
		hash = hash * prime + this.datainicio.hashCode();
		
		return hash;
	}
}