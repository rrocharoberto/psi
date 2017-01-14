package br.edu.univas.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Convenio;
import br.edu.univas.model.entity.Servico;

public class ServicoDAO {

	@Inject
	Servico servico;

	@Inject
	EntityManager em;

	public void save(Servico servico, Integer codigoConvenio) {
		
		System.out.println("Salvando servi�o: " + servico.getNome() + " para o conv�nio: " + codigoConvenio);
		Convenio convenio = em.find(Convenio.class, codigoConvenio);
		servico.setConvenio(convenio);
		em.persist(servico);
	}

	public Servico retrieveUser(String name) {
		return em.find(Servico.class, name);
	}
	
//não faz sentido
//	public List<Servico> retrieveAllServicos() {
//		TypedQuery<Servico> query = em.createNamedQuery("Servico.findAll", Servico.class);
//		List<Servico> list = query.getResultList();
//		return list;
//	}

	public List<Servico> findServiceByAgreement(Integer code) {
		TypedQuery<Servico> query = em.createNamedQuery("Servico.findByAgreement", Servico.class);
		query.setParameter("code", code);
		List<Servico> list = query.getResultList();
		return list;
	}

	public Map<Integer, Servico> retrieveServicosFromEstagiario(Long cpf) {
		TypedQuery<Servico> query = em.createNamedQuery("Servico.findServicosByEstagiario", Servico.class);
		query.setParameter("cpf", cpf);
		List<Servico> list = query.getResultList();
		
		HashMap<Integer, Servico> map = new HashMap<>();
		for (Servico s : list) {
			map.put(s.getCodigoServico(), s);
		}
		return map;
	}
}
