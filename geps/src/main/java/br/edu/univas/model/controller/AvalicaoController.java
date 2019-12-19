package br.edu.univas.model.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.edu.univas.model.dao.EstagiarioDAO;
import br.edu.univas.model.dao.FichaAvaliacaoDAO;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.FichaAvaliacao;
import br.edu.univas.model.util.Util;
import br.edu.univas.uteis.Constants;
import br.edu.univas.uteis.Uteis;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Named(value = "avaliacaoController")
@ViewScoped
public class AvalicaoController implements Serializable {

	private static final long serialVersionUID = -4072907444550334877L;

	@Inject
	EntityManager em;
	
	@Inject
	transient private EstagiarioDAO dao;
	
	@Inject
	transient private FichaAvaliacaoDAO fichaAvaliacaoDAO;
	
	@Inject
	transient private Util util;
	
	private List<Estagiario> estagiarios;
	
	//atributos usados na dialog
	
	private Estagiario estagiario;
	
	private List<FichaAvaliacao> avaliacoes;
	
	@PostConstruct
	public void init() {
		String matriculaProfessor = util.getMatriculaUserSession();
		estagiarios = dao.findByTeacher(matriculaProfessor);
		showSuccessMessage();
	}
	
	public void onload() {
	    //doNothing
	}
	
	public String avaliar(Estagiario estagiario) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put(Constants.ESTAGIARIO_SESSION, estagiario);
		return "realizarAvaliacao.xhtml?faces-redirect=true";
	}
	
	public List<Estagiario> getEstagiarios() {
		return estagiarios;
	}

	private Map<String, String> showSuccessMessage() {
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if ("success".equals(requestParameter.get("save"))) {
			Uteis.MensagemInfo("Avaliação cadastrada com sucesso.");
		}
		return requestParameter;
	}
	
	public void prepararAvaliacao(Estagiario estagiario) {
		this.estagiario = estagiario;
		avaliacoes = fichaAvaliacaoDAO.getFichaAvaliacaoByEstagiario(estagiario.getMatricula());
		System.out.println("Avaliações:" + avaliacoes);
	}

	public void gerarFichaAvaliacaoEstagio(FichaAvaliacao fichaAvaliacao) throws JRException, IOException {
		
		if (fichaAvaliacao != null) {
			gerarRelatorio("reports/FichaAvaliacaoEstagio.jasper", "FichaAvaliacaoEstagio", estagiario, fichaAvaliacao);
		} else {
			throw new IOException("Ficha de avaliação não encontrada para o estagiário: " + estagiario.getNome());
		}
	}

	private void gerarRelatorio(String reportFile, String reportFinalName, Estagiario estagiario, FichaAvaliacao fichaAvaliacao) throws JRException, IOException {
		try {
			// init();
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "inline; filename=" + reportFinalName + ".pdf");

			FacesContext.getCurrentInstance().responseComplete();

			InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream(reportFile);

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("atitude", fichaAvaliacao.getAtitude());
			parameters.put("cargaHoraria", fichaAvaliacao.getCarga_horaria());
			parameters.put("cognitiva", fichaAvaliacao.getCognitiva());
			parameters.put("habilidade", fichaAvaliacao.getHabilidade());
			parameters.put("mediaGeral", fichaAvaliacao.getMediaGeral());
			parameters.put("observacao", fichaAvaliacao.getObservacao());
			parameters.put("relatorioCientifico", fichaAvaliacao.getRelatorio_cientifico());
			parameters.put("aluno", estagiario.getNome());
			parameters.put("professor", estagiario.getOrientador().getNome());

			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, new JREmptyDataSource());

			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
						
			System.out.println("Relatório " + reportFinalName + " gerado.");
			servletOutputStream.flush();
			servletOutputStream.close();

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Estagiario getEstagiario() {
		return estagiario;
	}
	
	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}
	
	public List<FichaAvaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	
	public void setAvaliacoes(List<FichaAvaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

}