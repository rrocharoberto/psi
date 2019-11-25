package br.edu.univas.model.dto;

import java.util.Date;

public class EvolucaoReportTO {

	private Date dataEvolucao;
	private String descricao;

	public Date getDataEvolucao() {
		return dataEvolucao;
	}

	public void setDataEvolucao(Date dataEvolucao) {
		this.dataEvolucao = dataEvolucao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
