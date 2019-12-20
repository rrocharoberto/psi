package br.edu.univas.model.util;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

		File [] listPasta = new File(baseDir).listFiles();
//				FileUtils.listFiles(new File(baseDir), FileFilterUtils.trueFileFilter(), FileFilterUtils.trueFileFilter());

		EntityManager em = HibernateUtil.getEntityManager();

		try {
			for (File f1 : listPasta) {
				System.out.println("Processando diretório: " + f1.getCanonicalPath());
				String[] pasta = f1.getName().split("_");
				if (pasta.length > 1) {
					String numProntuario = pasta[1];
					
					Collection<File> listFiles = FileUtils.listFiles(new File(f1.getAbsolutePath()), FileFilterUtils.trueFileFilter(), null);

					TypedQuery<Registro> query = em.
							createQuery(
									"select r from Registro r where r.numeroProntuario like '%" +numProntuario+
									"%'", Registro.class);
					List<Registro> regList = query.getResultList();

					for (File f2 : listFiles) {
						String fullFileName = f2.getName();
						String [] names = fullFileName.split("_");
						if(names.length > 1) {
							String fileName = names[1];
							System.out.println("Processando arquivo: " + f2.getCanonicalPath());
							
							for (Registro reg : regList) {
								
								em.getTransaction().begin();
								if(reg != null) {
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
									} else {
										System.out.println("Não bateu os nomes: " + reg.getDeclaracao() + " com " + fileName);
										em.getTransaction().commit();
									}
								} else {
									em.getTransaction().commit();
									System.out.println("Registro não encontrado para prontuario: " + numProntuario);
								}
								//new Scanner(System.in).next();
							}
						} else {
							System.out.println("Parse errado do arquivo: " + fullFileName);
						}
					}
				}
			}
			System.out.println("Finalizou");
		}catch(

	Exception e)
	{
		e.printStackTrace();
	}
}

}
