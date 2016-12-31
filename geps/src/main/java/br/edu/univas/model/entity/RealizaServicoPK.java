package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the realizaservico database table.
 * 
 */
@Embeddable
public class RealizaServicoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, precision=131089)
	private long cpf;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer codigoServico;

	public RealizaServicoPK() {
	}
	public long getCpf() {
		return this.cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public Integer getCodigoServico() {
		return this.codigoServico;
	}
	public void setCodigoServico(Integer codigoservico) {
		this.codigoServico = codigoservico;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RealizaServicoPK)) {
			return false;
		}
		RealizaServicoPK castOther = (RealizaServicoPK)other;
		return 
			(this.cpf == castOther.cpf)
			&& this.codigoServico.equals(castOther.codigoServico);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.cpf ^ (this.cpf >>> 32)));
		hash = hash * prime + this.codigoServico.hashCode();
		
		return hash;
	}
}