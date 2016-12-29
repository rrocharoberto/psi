package br.edu.univas.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.example.uteis.Uteis;
import br.edu.univas.model.entity.Convenio;
import br.edu.univas.model.entity.Servico;

public class ServicoDAO {

	@Inject
	Servico servico;

	EntityManager em;

	public void save(Servico servico, Integer codigoConvenio) {
		em = Uteis.JpaEntityManager();
		
		System.out.println("Salvando serviço: " + servico.getNome() + " para o convênio: " + codigoConvenio);
		Convenio convenio = em.find(Convenio.class, codigoConvenio);
		servico.setConvenio(convenio);
		em.persist(servico);
	}

	public Servico retrieveUser(String name) {
		return em.find(Servico.class, name);
	}
	
//não faz sentido
//	public List<Servico> retrieveAllServicos() {
//		em = Uteis.JpaEntityManager();
//		TypedQuery<Servico> query = em.createNamedQuery("Servico.findAll", Servico.class);
//		List<Servico> list = query.getResultList();
//		return list;
//	}

	public List<Servico> findServiceByAgreement(Integer code) {
		em = Uteis.JpaEntityManager();
		TypedQuery<Servico> query = em.createNamedQuery("Servico.findByAgreement", Servico.class);
		query.setParameter("code", code);
		List<Servico> list = query.getResultList();
		return list;
	}
}
