package br.edu.univas.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class FichaAvaliacaoPK implements Serializable {

	//default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private String matricula_estagiario;

	@Column(name="data_avaliacao", insertable=false, updatable=false, unique=true, nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public FichaAvaliacaoPK() {
	}

	public String getMatricula_estagiario() {
		return matricula_estagiario;
	}

	public void setMatricula_estagiario(String matricula_estagiario) {
		this.matricula_estagiario = matricula_estagiario;
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((matricula_estagiario == null) ? 0 : matricula_estagiario.hashCode());
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
		FichaAvaliacaoPK other = (FichaAvaliacaoPK) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (matricula_estagiario == null) {
			if (other.matricula_estagiario != null)
				return false;
		} else if (!matricula_estagiario.equals(other.matricula_estagiario))
			return false;
		return true;
	}
	
}