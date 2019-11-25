package br.edu.univas.model.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.SessionImpl;

import br.edu.univas.model.dao.EvolucaoDAO;
import br.edu.univas.model.dto.EvolucaoReportTO;
import br.edu.univas.model.entity.Paciente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named(value = "relatorioController")
@ViewScoped
public class RelatorioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	@Inject
	transient private EvolucaoDAO evolucaoDAO;
	
	interface SpecificReportGenerator {
		public JasperPrint createJasperPrint(InputStream in, Map<String, Object> parameters) throws JRException;
	}
	
	public void gerarRelatorioListaEspera() throws JRException, IOException {
		Map<String, Object> param = new HashMap<>();
		final Connection connection = (Connection) em.unwrap(SessionImpl.class).connection();

		gerarRelatorio("reports/ListaDeEspera.jasper", "ListaDeEspera", param, new SpecificReportGenerator() {

			@Override
			public JasperPrint createJasperPrint(InputStream in, Map<String, Object> parameters) throws JRException {
				return JasperFillManager.fillReport(in, parameters, connection);
			}
		});
	}

	public void gerarRelatorioEvolucao(Paciente paciente) throws JRException, IOException {
		System.out.println("Gerando relatório de evolucação para numeroProntuario=" + paciente.getNumeroProntuario());
		Map<String, Object> param = new HashMap<>();
		param.put("paciente", paciente.getDadosPessoais().getNome());
		param.put("prontuario", paciente.getNumeroProntuario());

		//http://mauda.com.br/?p=1786
		Collection<EvolucaoReportTO> toList = evolucaoDAO.retrieveEvolucaoReportByPaciente(paciente.getNumeroProntuario());

		final JRBeanCollectionDataSource collectionDS = new JRBeanCollectionDataSource(toList, false);
		gerarRelatorio("reports/FichaDeEvolucao.jasper", "FichaDeEvolucao", param, new SpecificReportGenerator() {

			@Override
			public JasperPrint createJasperPrint(InputStream in, Map<String, Object> parameters) throws JRException {
				return JasperFillManager.fillReport(in, parameters, collectionDS);
			}
		});
	}

	
	private void gerarRelatorio(String reportFile, String reportFinalName, Map<String, Object> parameters, SpecificReportGenerator reportGenerator) throws JRException, IOException {
		try {

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "inline; filename=" + reportFinalName + ".pdf");

			FacesContext.getCurrentInstance().responseComplete();

			InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream(reportFile);

			JasperPrint jasperPrint = reportGenerator.createJasperPrint(inputStream, parameters);

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

}