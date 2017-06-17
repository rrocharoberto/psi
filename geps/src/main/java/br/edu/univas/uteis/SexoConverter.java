package br.edu.univas.uteis;

public class SexoConverter {

	public static String convert(String value) {
		String result = "";
		
		if (value != null && !value.isEmpty()) {
			switch (value) {
				case "M":
					result = "Masculino";
					break;
				case "F":
					result = "Feminino";
					break;
			}
		}
		return result;
	}

}
