package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the estagiario database table.
 * 
 */
@Entity
@NamedQuery(name="Estagiario.findAll", query="SELECT e FROM Estagiario e")
public class Estagiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cpf;

	private String areadeestagio;

	private String comentarios;

	private String curso;

	@Temporal(TemporalType.DATE)
	private Date datafimvigencia;

	@Temporal(TemporalType.DATE)
	private Date datainiciovigencia;

	private Integer matricula;

	//bi-directional one-to-one association to Dadospessoai
	@OneToOne
	@JoinColumn(name="cpf")
	private Dadospessoai dadospessoais;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="username")
	private Usuario usuario;

	public Estagiario() {
	}

	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getAreadeestagio() {
		return this.areadeestagio;
	}

	public void setAreadeestagio(String areadeestagio) {
		this.areadeestagio = areadeestagio;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Date getDatafimvigencia() {
		return this.datafimvigencia;
	}

	public void setDatafimvigencia(Date datafimvigencia) {
		this.datafimvigencia = datafimvigencia;
	}

	public Date getDatainiciovigencia() {
		return this.datainiciovigencia;
	}

	public void setDatainiciovigencia(Date datainiciovigencia) {
		this.datainiciovigencia = datainiciovigencia;
	}

	public Integer getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Dadospessoai getDadospessoais() {
		return this.dadospessoais;
	}

	public void setDadospessoais(Dadospessoai dadospessoais) {
		this.dadospessoais = dadospessoais;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}