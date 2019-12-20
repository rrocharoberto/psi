package br.edu.univas.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Registro;

public class RegistroDAO {

	@Inject
	private EntityManager em;

	public RegistroDAO() {
	}

	public Registro createNewRegistro(String numeroProntuario) {
		
		System.out.println("Salvando registro para prontuário: " + numeroProntuario);
		Registro reg = new Registro();
		reg.setNumeroProntuario(numeroProntuario);
		reg.setDeclaracao(null);
		reg.setTermoConsentimento(null);
		em.persist(reg);
		return reg;
	}

	public Registro retrieveRegistroByPaciente(String numeroProntuario) {
		TypedQuery<Registro> query = em.createNamedQuery("Registro.findRegistroByPaciente", Registro.class);
		query.setParameter("prontuario", numeroProntuario);
		List<Registro> list = query.getResultList();

		if(list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public void update(Registro registro) {
		em.merge(registro);
	}
}
