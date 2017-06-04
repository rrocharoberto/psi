package br.edu.univas.model.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.UploadedFile;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.dao.ProntuarioDAO;
import br.edu.univas.model.entity.Evolucao;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.model.entity.Prontuario;

@Named(value = "prontuarioController")
@ViewScoped
//TODO: excluir
public class ProntuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIR = "./uploads/";
	
	@Inject
	transient private Prontuario currentProntuario;

	private UploadedFile termoConsentimento;
	private UploadedFile declaracao;

	@Produces
	private List<Evolucao> evolucoes;

	@Inject
	transient private ProntuarioDAO dao;
	
	@Inject
	private ConsultarPacienteController consultarPacienteController;

	public void abrirProntuario(Paciente paciente) {
		Prontuario prontuario = dao.retrieveProntuarioFromPaciente(paciente.getCpf());
		if (prontuario == null) {
			System.out.println("Criando novo prontuário para paciente: " + paciente.getCpf());
			this.currentProntuario = dao.createNewProntuario(paciente.getCpf());
		} else {
			this.currentProntuario = prontuario;
		}
	}

	public void atualizarProntuario() {
		System.out.println("Atualizando prontuário: " + currentProntuario.getNumeroProntuario());

		dao.update(currentProntuario);

		consultarPacienteController.init();
	}

	public void uploadDocumentosProntuario() {
		System.out.println(
				"Upload de documentos do prontuário: " + currentProntuario.getNumeroProntuario());

		if (!this.declaracao.getFileName().equals("")) {
			System.out.println("Upload declaração: " + this.declaracao.getFileName());
			try {
				saveFileContents(declaracao);
				currentProntuario.setDeclaracao(declaracao.getFileName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!this.termoConsentimento.getFileName().equals("")) {
			System.out.println("Upload termo: " + this.termoConsentimento.getFileName());
			try {
				saveFileContents(termoConsentimento);
				currentProntuario.setTermoConsentimento(termoConsentimento.getFileName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Termo: " + currentProntuario.getTermoConsentimento() 
				+ " " + currentProntuario.getDeclaracao());
		dao.update(currentProntuario);
		
		consultarPacienteController.init();
		Uteis.MensagemInfo("Upload feito com sucesso.");
	}

	private void saveFileContents(UploadedFile uploadedFile) throws IOException {
		FileUtils.forceMkdir(new File(UPLOAD_DIR));
		File fileName = new File(UPLOAD_DIR 
				+ "prontuario_" 
				+ currentProntuario.getNumeroProntuario()
				+ "_" 
				+ uploadedFile.getFileName());
		FileUtils.writeByteArrayToFile(fileName, uploadedFile.getContents());
	}

	// setters e getters

	public Date getNow() {
		return new Date();
	}

	public Prontuario getCurrentProntuario() {
		return currentProntuario;
	}

	public void setCurrentProntuario(Prontuario currentProntuario) {
		this.currentProntuario = currentProntuario;
	}

	public UploadedFile getTermoConsentimento() {
		return termoConsentimento;
	}

	public void setTermoConsentimento(UploadedFile termoConsentimento) {
		this.termoConsentimento = termoConsentimento;
	}

	public UploadedFile getDeclaracao() {
		return declaracao;
	}

	public void setDeclaracao(UploadedFile declaracao) {
		this.declaracao = declaracao;
	}

	public List<Evolucao> getEvolucoes() {
		return evolucoes;
	}

	public void setEvolucoes(List<Evolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}

}