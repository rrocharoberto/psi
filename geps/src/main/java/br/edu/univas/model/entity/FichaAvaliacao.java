package br.edu.univas.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@NamedQuery(name="FichaAvaliacao.findByMatricula", query="SELECT fa FROM FichaAvaliacao fa WHERE fa.matricula_estagiario = :matricula_estagiario")
})
public class FichaAvaliacao implements Serializable {

	private static final long serialVersionUID = 3369792529049542403L;

	@Id
	@Column(unique=true, nullable=false)
	private String matricula_estagiario;

	@Column(nullable=false)
	private Integer atitude;

	@Column(nullable=false)
	private Integer cognitiva;

	@Column(nullable=false)
	private Integer habilidade;
	
	@Column(nullable=false)
	private Integer relatorio_cientifico;
	
	@Column(nullable=false)
	private Integer media_geral;
	
	@Column(nullable=false)
	private Integer carga_horaria;
	
	@Column(unique=true, nullable=false, length=255)
	private String observacao;

	public FichaAvaliacao() {
	}

	public String getMatricula_estagiario() {
		return matricula_estagiario;
	}

	public void setMatricula_estagiario(String matricula_estagiario) {
		this.matricula_estagiario = matricula_estagiario;
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

	public Integer getMedia_geral() {
		return media_geral;
	}

	public void setMedia_geral(Integer media_geral) {
		this.media_geral = media_geral;
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

}