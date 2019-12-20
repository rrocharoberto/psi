package br.edu.univas.model.util;

import java.io.File;
import java.util.Collection;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import br.edu.univas.model.entity.Registro;

public class UpdateUpload {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Wrong args: missing base directory");
			return;
		}
		String baseDir = args[0];

		Collection<File> listPasta = FileUtils.listFiles(new File(baseDir), FileFilterUtils.trueFileFilter(), null);

		EntityManager em = HibernateUtil.getEntityManager();

		try {
			for (File f1 : listPasta) {
				System.out.println("Processando diretório: " + f1.getCanonicalPath());
				String[] pasta = f1.getName().split("_");
				if (pasta.length > 1) {
					String numProntuario = pasta[1];
					
					Collection<File> listFiles = FileUtils.listFiles(new File(baseDir+ "/" + pasta), FileFilterUtils.trueFileFilter(), null);

					for (File f2 : listFiles) {
						String[] file = f1.getName().split("_");
						System.out.println("Processando arquivo: " + f2.getCanonicalPath());
						
						String fileName = file[1];
	
						em.getTransaction().begin();
						Registro reg = em.find(Registro.class, numProntuario);
						if (reg.getDeclaracao().equals(fileName)) {
							reg.setDeclaracaoContent(FileUtils.readFileToByteArray(f2));
							em.merge(reg);
							em.getTransaction().commit();
							System.out.println("Updated declaracao of registro: " + reg.getNumeroProntuario());
						} else if (reg.getTermoConsentimento().equals(fileName)) {
							reg.setTermoContent(FileUtils.readFileToByteArray(f2));
							em.merge(reg);
							em.getTransaction().commit();
							System.out.println("Updated termo of registro: " + reg.getNumeroProntuario());
						}
						new Scanner(System.in).next();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
