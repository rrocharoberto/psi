package br.edu.univas.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.dto.EvolucaoReportTO;
import br.edu.univas.model.entity.Evolucao;

public class EvolucaoDAO {

	@Inject
	private EntityManager em;

	public EvolucaoDAO(EntityManager em) {
		this.em = em;
	}

	public EvolucaoDAO() {
	}

	public void save(Evolucao evolucao) {
		em.persist(evolucao);
	}

	public List<Evolucao> retrieveByPaciente(String numeroProntuario) {
		TypedQuery<Evolucao> query = em.createNamedQuery("Evolucao.findByProntuario", Evolucao.class);
		query.setParameter("prontuario", numeroProntuario);
		List<Evolucao> list = query.getResultList();
		return list;
	}

	public List<EvolucaoReportTO> retrieveEvolucaoReportByPaciente(String numeroProntuario) {
		TypedQuery<Evolucao> query = em.createNamedQuery("Evolucao.findByProntuario", Evolucao.class);
		query.setParameter("prontuario", numeroProntuario);
		List<Evolucao> list = query.getResultList();
		
		List<EvolucaoReportTO> listResult = new ArrayList<EvolucaoReportTO>();
		for (Evolucao ev : list) {
			EvolucaoReportTO to = new EvolucaoReportTO();
			to.setDataEvolucao(ev.getId().getData());
			to.setDescricao(ev.getDescricao());
			listResult.add(to);
		}
		return listResult;
	}

	
	public void update(Evolucao evolucao) {
		em.merge(evolucao);
	}

}
