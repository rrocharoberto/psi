package br.edu.univas.model.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.univas.model.entity.Acompanha;
import br.edu.univas.model.entity.AcompanhaPK;
import br.edu.univas.model.entity.Estagiario;
import br.edu.univas.model.entity.Prontuario;

public class AcompanhaDAO {

	@Inject
	EntityManager em;

	public AcompanhaDAO() {
	}

	public void save(Acompanha acompanha) {
		em.persist(acompanha);
	}
	
	public void createNewAcompanha(AcompanhaPK id, Estagiario estagiario, Prontuario prontuario) {
		Acompanha acompanha = new Acompanha();
		acompanha.setId(id);
		acompanha.setAtivo(true);
		acompanha.setEstagiario(estagiario);
		acompanha.setProntuario(prontuario);
		
		this.save(acompanha);
	}

	public Acompanha retrieveAcompanha(AcompanhaPK id) {
		return em.find(Acompanha.class, id);
	}

	public void update(Acompanha acompanha) {
		em.merge(acompanha);
	}

}
