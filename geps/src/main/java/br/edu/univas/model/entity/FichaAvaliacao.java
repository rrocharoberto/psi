package br.edu.univas.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the area database table.
 * 
 */
@Entity
@Table(name="fichaAvaliacao")
@NamedQueries({
	@NamedQuery(name="FichaAvaliacao.findByMatricula", query="SELECT fa FROM FichaAvaliacao fa WHERE fa.id.matricula_estagiario = :matricula_estagiario")
})
public class FichaAvaliacao implements Serializable {

	private static final long serialVersionUID = 3369792529049542403L;

	@EmbeddedId
	private FichaAvaliacaoPK id;

	@Column(nullable=false)
	private Integer atitude;

	@Column(nullable=false)
	private Integer cognitiva;

	@Column(nullable=false)
	private Integer habilidade;
	
	@Column(nullable=false)
	private Integer relatorio_cientifico;
	
	@Column(name="media_geral", nullable=false)
	private Integer mediaGeral;
	
	@Column(nullable=false)
	private Integer carga_horaria;
	
	@Column(unique=true, nullable=false, length=255)
	private String observacao;
		
	public FichaAvaliacao() {
	}

	public FichaAvaliacao(String matriculaEstagiario, Date data) {
		this.id = new FichaAvaliacaoPK();
		this.id.setMatricula_estagiario(matriculaEstagiario);
		this.id.setData(data);
	}

	public FichaAvaliacaoPK getId() {
		return id;
	}
	
	public void setId(FichaAvaliacaoPK id) {
		this.id = id;
	}
	
	public Integer getAtitude() {
		return atitude;
	}

	public void setAtitude(Integer atitude) {
		this.atitude = atitude;
	}

	public Integer getCognitiva() {
		return cognitiva;
	}

	public void setCognitiva(Integer cognitiva) {
		this.cognitiva = cognitiva;
	}

	public Integer getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(Integer habilidade) {
		this.habilidade = habilidade;
	}

	public Integer getRelatorio_cientifico() {
		return relatorio_cientifico;
	}

	public void setRelatorio_cientifico(Integer relatorio_cientifico) {
		this.relatorio_cientifico = relatorio_cientifico;
	}

	public Integer getMediaGeral() {
		return mediaGeral;
	}

	public void setMediaGeral(Integer mediaGeral) {
		this.mediaGeral = mediaGeral;
	}

	public Integer getCarga_horaria() {
		return carga_horaria;
	}

	public void setCarga_horaria(Integer carga_horaria) {
		this.carga_horaria = carga_horaria;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "FichaAvaliacao [matricula_estagiario=" + id.getMatricula_estagiario() + ", mediaGeral=" + mediaGeral
				+ ", carga_horaria=" + carga_horaria + ", data=" + id.getData() + "]\n";
	}

}