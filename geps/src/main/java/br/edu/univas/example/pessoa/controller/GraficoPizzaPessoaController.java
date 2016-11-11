package br.edu.univas.example.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.edu.univas.example.repository.PessoaRepository;

@Named(value="graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;
		
	
	private PieChartModel pieChartModel;

	private PieChartModel pieChartGenerModel;

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}
	
	public PieChartModel getPieChartGenerModel() {
		return pieChartGenerModel;
	}
	
	@PostConstruct
	public  void init(){
		
		this.pieChartModel = new PieChartModel();
		this.pieChartGenerModel = new PieChartModel();
		
		this.MontaGraficoOrigem();
		this.MontaGraficoGenero();
	}
	
	/***
	 * MONTA O GRÁFICO NA PÁGINA - Origem
	 */
	private void MontaGraficoOrigem(){
		
		//CONSULTA OS DADOS PARA MONTAR O GRÁFICO
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa();
		
		//INFORMANDO OS VALORES PARA MONTAR O GRÁFICO
		hashtableRegistros.forEach((chave,valor) -> {
			pieChartModel.set(chave, valor);
		});
		
		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");
		pieChartModel.setDiameter(100);
	}

	/***
	 * MONTA O GRÁFICO NA PÁGINA - Gênero
	 */
	private void MontaGraficoGenero(){
		
		//CONSULTA OS DADOS PARA MONTAR O GRÁFICO
		Hashtable<String, Integer> hashtableRegistrosSexo = pessoaRepository.GetGenrePessoa();
		
		
		//INFORMANDO OS VALORES PARA MONTAR O GRÁFICO
		hashtableRegistrosSexo.forEach((chave,valor) -> {
			pieChartGenerModel.set(chave, valor);
		});
		
		hashtableRegistrosSexo.forEach((chave,valor) -> {
			pieChartGenerModel.set(chave, valor);
		});
		pieChartGenerModel.setTitle("Total de Pessoas cadastrado por Sexo");
		pieChartGenerModel.setShowDataLabels(true);
		pieChartGenerModel.setLegendPosition("w");
		pieChartGenerModel.setDiameter(50);
		
	}
}
