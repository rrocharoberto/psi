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

import br.edu.univas.model.dao.RegistroDAO;
import br.edu.univas.model.entity.Evolucao;
import br.edu.univas.model.entity.Paciente;
import br.edu.univas.model.entity.Registro;
import br.edu.univas.uteis.Uteis;

@Named(value = "registroController")
@ViewScoped
public class RegistroController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private Registro currentRegistro;

	private UploadedFile termoConsentimento;
	private UploadedFile declaracao;

	@Produces
	private List<Evolucao> evolucoes;

	@Inject
	transient private RegistroDAO dao;
	
	@Inject
	private ConsultarPacienteController consultarPacienteController;

	public void abrirRegistro(Paciente paciente) {
		Registro registro = dao.retrieveRegistroByPaciente(paciente.getNumeroProntuario());
		if (registro == null) {
			System.out.println("Criando novo registro para paciente: " + paciente.getNumeroProntuario());
			this.currentRegistro = dao.createNewRegistro(paciente.getNumeroProntuario());
			this.currentRegistro.setPaciente(paciente);
		} else {
			this.currentRegistro = registro;
		}
	}

	public void atualizarRegistro() {
		System.out.println("Atualizando prontuário: " + currentRegistro.getPaciente().getNumeroProntuario());

		dao.update(currentRegistro);

		consultarPacienteController.init();
	}

	public void uploadDocumentos() {
		System.out.println("Upload de documentos do prontuário: " + currentRegistro.getPaciente().getNumeroProntuario());

		if (!this.declaracao.getFileName().equals("")) {
			System.out.println("Upload declaração: " + this.declaracao.getFileName());
			try {
				saveFileContents(declaracao);
				currentRegistro.setDeclaracao(declaracao.getFileName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!this.termoConsentimento.getFileName().equals("")) {
			System.out.println("Upload termo: " + this.termoConsentimento.getFileName());
			try {
				saveFileContents(termoConsentimento);
				currentRegistro.setTermoConsentimento(termoConsentimento.getFileName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Termo: " + currentRegistro.getTermoConsentimento() 
				+ " " + currentRegistro.getDeclaracao());
		dao.update(currentRegistro);
		
		consultarPacienteController.init();
		Uteis.MensagemInfo("Upload feito com sucesso.");
	}

	private void saveFileContents(UploadedFile uploadedFile) throws IOException {
		FileUtils.forceMkdir(new File(Uteis.UPLOAD_DIR));
		String newFileName = Uteis.generateFileNameByPaciente(currentRegistro.getPaciente().getNumeroProntuario(), uploadedFile.getFileName());
		File fileName = new File(newFileName);
		FileUtils.writeByteArrayToFile(fileName, uploadedFile.getContents());
	}

	// setters e getters

	public Date getNow() {
		return new Date();
	}

	public Registro getCurrentRegistro() {
		return currentRegistro;
	}
	
	public void setCurrentRegistro(Registro currentRegistro) {
		this.currentRegistro = currentRegistro;
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