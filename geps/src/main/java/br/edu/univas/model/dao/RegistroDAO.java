package br.edu.univas.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.univas.model.entity.Documento;
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

	private void checkAndCreateDocumento(String numeroProntuario) {
		try {
			em.createQuery("select numeroProntuario from Documento where numeroProntuario = :np")
			.setParameter("np", numeroProntuario)
			.getSingleResult();
		} catch (NoResultException e) {
			Documento doc = new Documento();
			doc.setNumeroProntuario(numeroProntuario);
			em.persist(doc);
		}
	}

	public void updateDeclaracao(String numeroProntuario, byte[] content) {
		checkAndCreateDocumento(numeroProntuario);
		
		em.createQuery("update Documento set declaracaoContent = :content where numeroProntuario = :np")
			.setParameter("content", content)
			.setParameter("np", numeroProntuario)
			.executeUpdate();
	}

	public void updateTermo(String numeroProntuario, byte[] content) {
		checkAndCreateDocumento(numeroProntuario);

		em.createQuery("update Documento set termoContent = :content where numeroProntuario = :np")
			.setParameter("content", content)
			.setParameter("np", numeroProntuario)
			.executeUpdate();
	}

	public byte [] retrieveDeclaracao(String numeroProntuario) {
		System.out.println("Buscando conteúdo de declaração do prontuário: " + numeroProntuario);
		Query query = em.createNativeQuery("select declaracaoContent from documento where numeroProntuario = :np");
		query.setParameter("np", numeroProntuario);
		Object obj = query.getSingleResult();
		if(obj != null) {
			byte [] bytes = (byte[]) obj;
			System.out.println("Size of Declaracao content: " + bytes.length);
			return bytes;
		}
		return null;
	}
	
	public byte [] retrieveTermo(String numeroProntuario) {
		System.out.println("Buscando conteúdo de termo do prontuário: " + numeroProntuario);
		Query query = em.createNativeQuery("select termoContent from documento where numeroProntuario = :np");
		query.setParameter("np", numeroProntuario);
		Object obj = query.getSingleResult();
		if(obj != null) {
			byte [] bytes = (byte[]) obj;
			System.out.println("Size of Termo content: " + bytes.length);
			return bytes;
		}
		return null;
	}
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
