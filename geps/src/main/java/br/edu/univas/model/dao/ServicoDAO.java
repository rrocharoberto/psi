package br.edu.univas.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Area;
import br.edu.univas.model.entity.Servico;

public class ServicoDAO {

	@Inject
	EntityManager em;

	public void save(Servico servico, Integer codigoArea) {
		
		System.out.println("Salvando serviço: " + servico.getNome() + " para o área: " + codigoArea);
		Area area = em.find(Area.class, codigoArea);
		servico.setArea(area);
		em.persist(servico);
	}
	
	public Servico retrieveUser(String name) {
		return em.find(Servico.class, name);
	}
	
	public List<Servico> retrieveAllServicos() {
		TypedQuery<Servico> query = em.createNamedQuery("Servico.findAll", Servico.class);
		List<Servico> list = query.getResultList();
		return list;
	}

	public List<Servico> findServiceByArea(Integer code) {
		TypedQuery<Servico> query = em.createNamedQuery("Servico.findByArea", Servico.class);
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

	public Map<Integer, Servico> retrieveAllServicosAsMap() {
		return convertToMap(retrieveAllServicos());
	}
	
	private Map<Integer, Servico> convertToMap(List<Servico> list) {		
		HashMap<Integer, Servico> map = new HashMap<>();
		for (Servico s : list) {
			map.put(s.getCodigoServico(), s);
		}
		return map;
	}
//TODO: ver se vai precisar deletar em algum outro lugar - usa NativeQuery
//	public void deleteRealizaServico(RealizaServicoPK realizaServicoPK) {
//		Query query = em.createNamedQuery("RealizaServico.deleteRealizaServico");
//		query.setParameter("cpf", realizaServicoPK.getCpf());
//		query.setParameter("codigoServico", realizaServicoPK.getCodigoServico());
//		query.executeUpdate();
//	}

}
