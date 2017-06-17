package br.edu.univas.uteis;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//look at: http://pt.stackoverflow.com/questions/59444/primefaces-datatable-formata%C3%A7%C3%A3o-de-n%C3%BAmeros
@FacesConverter("PhoneNumberConverter")
public class PhoneNumberConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	if(value.length() > 0) {
	    	value = value.replace("(", "-");
	    	value = value.replace(")", "-");
	        String[] parts = value.split("-");
	        return Long.parseLong(join(parts));
    	} else {
    		return null;
    	}
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	System.out.println("PhoneNumberConverter: getAsString: " + value);
        String valueAsString = value.toString();
        if (valueAsString.equals("0") || valueAsString.isEmpty()) {
        	return valueAsString;
        }
        
        Object obj = component.getAttributes().get("isCellPhone");
        if(obj != null && obj instanceof String) {
	        Boolean isCellPhone = Boolean.valueOf((String) obj);
	        
	        int sizePart1 = 4;
	        int sizePart2 = sizePart1 + 4;
	        if(isCellPhone) {
	        	sizePart2 = sizePart1 + 5;
	        }
	        
	        int len = valueAsString.length();
	        
	        if ((isCellPhone && len < 11) || (!isCellPhone && len < 10)) {
	        	valueAsString = StringUtil.longToString((Long) value, isCellPhone ? 11 : 10);
	        	len = valueAsString.length();
	        }
	        
	        String part1 = valueAsString.substring(0, len - sizePart2);
	        String part2 = valueAsString.substring(len - sizePart2, len - sizePart1);
	        String part3 = valueAsString.substring(len - sizePart1, len);
	        
	        return formatPhone(part1, part2, part3);
        } else {
        	String errorMessage = "PhoneNumberConverter: Invalid property isCellPhone: expected Boolean. Found: " + obj;
        	new RuntimeException(errorMessage).printStackTrace();
        	return errorMessage;
        }
    }

    private String formatPhone(String part1, String part2, String part3) {
        return "(".concat(part1).concat(")").concat(part2).concat("-").concat(part3);
    }

    private String join(String[] parts) {
        StringBuilder sb = new StringBuilder();
        System.out.println("parts: ");

        for(String part : parts) {
	        System.out.print(part);
            sb.append(part);
        }
        System.out.println();
        return sb.toString();
    }
}
