package br.edu.univas.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import br.edu.univas.uteis.StringUtil;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the estagiario database table.
 * 
 */
@Entity
@Table(name="estagiario")

@NamedQueries ({
	@NamedQuery(name="Estagiario.findAll", query="SELECT e FROM Estagiario e"),
	//TODO: corrigir findAllAtivos
	@NamedQuery(name="Estagiario.findAllAtivos", query="SELECT e FROM Estagiario e WHERE e.usuario.active = true")
})

@NamedNativeQueries({//TODO: verificar se vai precisar deletar alguma coisa
    @NamedNativeQuery( name="Estagiario.deleteRealizaServico", 
					query="DELETE FROM RealizaServico r WHERE r.cpf = :cpf AND r.codigoServico = :codigoServico"
    )
})


public class Estagiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String matricula;

	@Column(nullable=false, length=500)
	private String comentarios;

	@Column(nullable=false, length=50)
	private String curso;
	
	@Column(precision=131089)
	private Long telefone;

	@Temporal(TemporalType.DATE)
	private Date dataFimVigencia;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dataInicioVigencia;

	@Column(nullable=false, length=50)
	private String nome;

	//bi-directional many-to-one association to Professor
	@ManyToOne
	@JoinColumn(name="orientador")
	private Professor orientador;

	//bi-directional one-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="matricula", nullable=false, insertable=false, updatable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Evolucao
	@OneToMany(mappedBy="estagiario")
	private List<Evolucao> evolucoes;

	//bi-directional many-to-one association to Paciente
	@OneToMany(mappedBy="estagiario")
	private List<Paciente> pacientes;

	public Estagiario() {
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public Date getDataFimVigencia() {
		return this.dataFimVigencia;
	}

	public void setDataFimVigencia(Date dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

	public Date getDataInicioVigencia() {
		return this.dataInicioVigencia;
	}

	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getOrientador() {
		return this.orientador;
	}

	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Evolucao> getEvolucoes() {
		return this.evolucoes;
	}

	public void setEvolucoes(List<Evolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}

	public Evolucao addEvolucoe(Evolucao evolucoe) {
		getEvolucoes().add(evolucoe);
		evolucoe.setEstagiario(this);

		return evolucoe;
	}

	public Evolucao removeEvolucoe(Evolucao evolucoe) {
		getEvolucoes().remove(evolucoe);
		evolucoe.setEstagiario(null);

		return evolucoe;
	}

	public List<Paciente> getPacientes() {
		return this.pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public Long getTelefone() {
		return this.telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefoneString() {
		return StringUtil.longToString(this.telefone, 11);
	}


	public Paciente addPaciente(Paciente paciente) {
		getPacientes().add(paciente);
		paciente.setEstagiario(this);

		return paciente;
	}

	public Paciente removePaciente(Paciente paciente) {
		getPacientes().remove(paciente);
		paciente.setEstagiario(null);

		return paciente;
	}

}