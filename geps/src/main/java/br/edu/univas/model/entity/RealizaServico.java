package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the realizaservico database table.
 * 
 */
@Entity
@Table(name="realizaservico")

@NamedQueries({
	@NamedQuery(name="RealizaServico.findAll",
				query="SELECT r FROM RealizaServico r")
})

@NamedNativeQueries({
    @NamedNativeQuery( name="RealizaServico.deleteRealizaServico", 
					query="DELETE FROM RealizaServico r WHERE r.cpf = :cpf AND r.codigoServico = :codigoServico"
    )
})

public class RealizaServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RealizaServicoPK id;

	//bi-directional many-to-one association to Estagiario
	@ManyToOne
	@JoinColumn(name="cpf", nullable=false, insertable=false, updatable=false)
	private Estagiario estagiario;

	//bi-directional many-to-one association to Servico
	@ManyToOne
	@JoinColumn(name="codigoServico", nullable=false, insertable=false, updatable=false)
	private Servico servico;

	public RealizaServico() {
	}

	public RealizaServicoPK getId() {
		return this.id;
	}

	public void setId(RealizaServicoPK id) {
		this.id = id;
	}

	public Estagiario getEstagiario() {
		return this.estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}