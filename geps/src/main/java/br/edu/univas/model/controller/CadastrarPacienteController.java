package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.primefaces.event.FlowEvent;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.DadosPessoaisDAO;
import br.edu.univas.model.dao.PacienteDAO;
import br.edu.univas.model.entity.DadosPessoais;
import br.edu.univas.model.entity.Paciente;

@Named(value = "cadastrarPacienteController")
@ViewScoped
public class CadastrarPacienteController implements Serializable {

	private static final long serialVersionUID = 2569574536599811497L;

	@Inject
	private DadosPessoaisController dadosPessoaisController;

	@Inject
	private PacienteController pacienteController;

	@Inject
	transient private PacienteDAO pacienteDAO;
	
	public String salvarPaciente() {
		dadosPessoaisController.save();
		
		pacienteController.getCurrentPaciente().setCpf(dadosPessoaisController.getDadosPessoais().getCpf());
		pacienteController.getCurrentPaciente().setDadosPessoais(dadosPessoaisController.getDadosPessoais());
		pacienteDAO.save(pacienteController.getCurrentPaciente());

		Uteis.MensagemInfo("Paciente cadastrado com sucesso.");
		
		dadosPessoaisController.reset();
		pacienteController.reset();
		
		return null; //recarrega a página de cadastro de paciente
	}

	public String onFlowProcess(FlowEvent event) {
		System.out.println("Trocou para da aba: " + event.getOldStep() 
				+ " para a aba: " + event.getNewStep()
				+ " Nome: " + dadosPessoaisController.getDadosPessoais().getNome());

		pacienteController.getCurrentPaciente().setDadosPessoais(dadosPessoaisController.getDadosPessoais());
		return event.getNewStep();
	}

	
	/**Teste**/

	public static void main(String[] args) {
		new CadastrarPacienteController().teste();
	}
	
	public void teste(){
		
		EntityManager em = Persistence.createEntityManagerFactory("unit_app").createEntityManager(); 
		
		Paciente p1 = em.find(Paciente.class, 111L);
		System.out.println(p1.getDataEntrada());

		System.out.println(p1.getDadosPessoais().getNome());
		pacienteDAO = new PacienteDAO(em);
		DadosPessoaisDAO dadosDAO = new DadosPessoaisDAO(em);
		
		em.getTransaction().begin();

		DadosPessoais dp = new DadosPessoais();
		dp.setAtivo(true);
		dp.setCelular(1111L);
		dp.setCpf(111);
		dp.setDataNascimento(new Date());
		dp.setEstadoCivil("1");
		dp.setNacionalidade("sss");
		dp.setNaturalidade("ddd");
		dp.setNome("aaaaaaaaaaaaa");
		dp.setRg(22222L);
		dp.setSexo("M");
		dp.setTelefone(2222L);
		dp.setUf("MG");
		
		dadosDAO.save(dp);
		
		Paciente p = new Paciente();
		p.setCpf(dp.getCpf());
		p.setDadosPessoais(dp);
		p.setDataEntrada(new Date());
		p.setDecisao(1);
		p.setOrigem(2);
		
		pacienteDAO.save(p);
		
		em.getTransaction().commit();
	}

}