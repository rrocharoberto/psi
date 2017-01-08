package br.edu.univas.model.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.DadosPessoais;

public class DadosPessoaisDAO {

	@Inject
	EntityManager em;
	
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

	public void inativate(Long cpf) {
		TypedQuery<DadosPessoais> query = em.createNamedQuery("DadosPessoais.findByCPF", DadosPessoais.class);
		query.setParameter("cpf", cpf);
		DadosPessoais dados = query.getSingleResult();
		if (dados != null) {
			dados.setAtivo(false);
			em.merge(dados);
		} else {
			System.err.println("Dados pessoais não encontrado para o cpf: " + cpf);
		}

	}

}
