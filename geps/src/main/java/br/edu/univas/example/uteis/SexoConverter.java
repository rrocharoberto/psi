package br.edu.univas.example.uteis;

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
