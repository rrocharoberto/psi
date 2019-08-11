package br.edu.univas.uteis;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
	
	public static String simpleTextToSha256(String text) {
		if (text == null) {
			return null;
		}
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] textCrip = digest.digest(text.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(textCrip);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;
	}

	public static boolean isNullOrEmpty(String text) {
		if (text == null) {
			return true;
		}
		return text.isEmpty();
	}

}
