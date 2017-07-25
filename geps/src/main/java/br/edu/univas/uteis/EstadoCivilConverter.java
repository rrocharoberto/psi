package br.edu.univas.uteis;

public class EstadoCivilConverter {

	public static String convert(String value) {
		String result = "";
		
		if (value != null && !value.isEmpty()) {
			switch (value) {
				case "0":
					result = "Solteiro";
					break;
				case "1":
					result = "Casado";
					break;
				case "2":
					result = "Divorciado";
					break;
				case "3":
					result = "Viúvo";
					break;
			}
		}
		return result;
	}

}
