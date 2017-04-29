package br.edu.univas.example.uteis;

public class StringUtil {

	public static String longToString(Long value, int qtdChars) {
		StringBuffer stringResult = new StringBuffer("");
		
		if (value != null) {
			int difference = qtdChars - value.toString().length();
			
			while (stringResult.length() < difference) {
				stringResult.append("0");
			}
			
			stringResult.append(value.toString());
		}
		
		return stringResult.toString();
	}
	
}
