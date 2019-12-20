package br.edu.univas.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static final EntityManagerFactory emFactory = buildSessionFactory();

	private static EntityManagerFactory buildSessionFactory() {
		try {
			// Create the Factory
			return Persistence.createEntityManagerFactory("unit_app"); 
		} catch (Throwable e) {
			// Make sure you log the exception , as it might be swallowed
			System.err.println("Error creating EntityManagerFactory ." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	// metodo que utilizaremos para obter o EntityManager
	public static EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}
}