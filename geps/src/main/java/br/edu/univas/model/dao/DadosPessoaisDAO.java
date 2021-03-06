package br.edu.univas.model.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.univas.model.entity.DadosPessoais;

public class DadosPessoaisDAO {

	@Inject
	private EntityManager em;
	
	public DadosPessoaisDAO(EntityManager em) {
		this.em = em;
	}
	
	public DadosPessoaisDAO() {
	}

	public void save(DadosPessoais dadosPessoais) {
		em.persist(dadosPessoais);
	}

	public DadosPessoais retrieveDadosPessoais(Long cpf) {
		return em.find(DadosPessoais.class, cpf);
	}

}
