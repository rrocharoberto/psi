package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.uteis.Uteis;

@Named(value = "pacienteController")
@ViewScoped
public class PacienteController implements Serializable, Validator {
	
	private static final long serialVersionUID = -5587018526982444175L;

	@Inject
	Paciente currentPaciente;

	@Inject
	transient private PacienteDAO dao;

	@Inject
	transient private EstagiarioDAO estagiarioDAO;
	
	@Inject
	private List<Estagiario> estagiarios;
	
	private String currentEstagiario;
	
	@PostConstruct
	public void reset() {
		currentPaciente = new Paciente();
		estagiarios = estagiarioDAO.retrieveEstagiariosAtivos();
	}

	public void editar(String numeroProntuario) {
		this.currentPaciente = dao.retrievePaciente(numeroProntuario);
		if(currentPaciente.getEstagiario() != null) {
			this.currentEstagiario = currentPaciente.getEstagiario().getMatricula();
		}
		this.minFirstDate = currentPaciente.getDadosPessoais().getDataNascimento();
		this.minLastDate = currentPaciente.getDataEntrada();
	}

	public void atualizarPaciente() {
		setEstagiario();
		dao.update(currentPaciente);
		currentEstagiario = null; //para não aparecer o mesmo estagiário para outro paciente.
		Uteis.MensagemInfo("Dados salvos com sucesso.");
	}

	public void setEstagiario() {
		System.out.println("Chamou o setEstagiario no pacienteController: currentEstagiario" + currentEstagiario);
		Estagiario estagiario = estagiarioDAO.retrieveEstagiario(currentEstagiario);
		currentPaciente.setEstagiario(estagiario);
	}
	
	public void inativarPaciente(Paciente paciente) {
		dao.inativate(paciente.getNumeroProntuario());
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String numeroProntuario = (String) value;
		System.out.println("ProntuarioDisponivelValidator:validate numeroProntuario: " + numeroProntuario);

		Paciente obj = dao.retrievePaciente(numeroProntuario);
		//se achou um objeto do banco com o número do prontuário novo, e esse número é diferente do atual que está editando
        if (obj != null && !obj.getNumeroProntuario().equals(currentPaciente.getNumeroProntuario())) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"O número do prontuário " + numeroProntuario + " já está sendo utilizado!",
					"Digite outro prontuário."));
        }
	}


	public Paciente getCurrentPaciente() {
		return currentPaciente;
	}
	
	public void setCurrentPaciente(Paciente currentPaciente) {
		this.currentPaciente = currentPaciente;
	}

	/*************************************/
	/** controle de data mínima e máxima */

	private Date minFirstDate = null;
	private Date minLastDate = new Date();

	public void firstDateChoosen() {
		minLastDate = currentPaciente.getDataEntrada();
		System.out.println("minLastDate updated to: " + minLastDate);
	}

	public Date getMinFirstDate() {
		return minFirstDate;
	}

	public Date getMinLastDate() {
		return minLastDate;
	}

	public Date getNow() {
		return new Date();
	}
	
	public String getCurrentEstagiario() {
		return currentEstagiario;
	}
	
	public void setCurrentEstagiario(String currentEstagiario) {
		this.currentEstagiario = currentEstagiario; //vem a matrícula do estagiário (do combo)
	}
	
	public List<Estagiario> getEstagiarios() {
		return estagiarios;
	}

}