package br.edu.univas.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.FilaEspera;

public class FilaEsperaDAO {

	@Inject
	EntityManager em;

	public FilaEsperaDAO(EntityManager em) {
		this.em = em;
	}

	public FilaEsperaDAO() {
	}

	public void save(FilaEspera filaEspera) {
		em.persist(filaEspera);
	}

	public FilaEspera retrieveFilaEspera(Long cpf) {
		return em.find(FilaEspera.class, cpf);
	}

	public List<FilaEspera> retrieveAllFilaEspera() {
		TypedQuery<FilaEspera> query = em.createNamedQuery("FilaEspera.findAll", FilaEspera.class);
		List<FilaEspera> list = query.getResultList();
		return list;
	}
	
	public Map<Long, FilaEspera> retrieveAllFilaEsperaAsMap() {
		HashMap<Long, FilaEspera> map = new HashMap<>();
		for (FilaEspera p : retrieveAllFilaEspera()) {
			map.put(p.getCpf(), p);
		}
		return map;
	}

	public void update(FilaEspera filaEspera) {
		em.merge(filaEspera);
	}

}
