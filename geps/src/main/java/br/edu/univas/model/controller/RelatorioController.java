package br.edu.univas.model.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Named(value = "relatorioController")
@ViewScoped
public class RelatorioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	public void gerarRelatorioListaEspera() throws JRException, IOException {
		
		gerarRelatorio("reports/ListaDeEspera.jasper", "ListaDeEspera");
	}

	private void gerarRelatorio(String reportFile, String reportFinalName) throws JRException, IOException {
		try {

			Connection connection = (Connection) em.unwrap(SessionImpl.class).connection();
			//System.out.println("Connection: " + connection);
			// init();
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "inline; filename=" + reportFinalName + ".pdf");

			FacesContext.getCurrentInstance().responseComplete();

			InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream(reportFile);

			 //System.out.println("inputStream: " + inputStream);
//			 System.out.println("sourceFileName: " +
//			 FacesContext.getCurrentInstance().getExternalContext()
//			 .getResource("./reports/coffee.jpg").getPath());

			Map<String, Object> parameters = new HashMap<>();
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, connection);

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