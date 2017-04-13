package br.edu.univas.example.uteis;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//look at: http://pt.stackoverflow.com/questions/59444/primefaces-datatable-formata%C3%A7%C3%A3o-de-n%C3%BAmeros
@FacesConverter("RGConverter")
public class RGConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	if(value.length() > 0) {
    		value = value.replace(".", "-");
	        String[] parts = value.split("-");
	        return Long.parseLong(join(parts));
    	} else {
    		return null;
    	}
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	System.out.println("RGConverter: getAsString: " + value);
        String valueAsString = value.toString();
        if (valueAsString.equals("0") || valueAsString.isEmpty()) {
        	return valueAsString;
        }
        
        int len = valueAsString.length();
        if (len < 8) {
        	valueAsString = StringUtil.longToString((Long) value, 8);
        	len = valueAsString.length();
        }
        
        String part1 = valueAsString.substring(0, len - 6);
        String part2 = valueAsString.substring(len - 6, len - 3);
        String part3 = valueAsString.substring(len - 3, len);
    
        return formatRG(part1, part2, part3);
    }

    private String formatRG(String part1, String part2, String part3) {
        return part1.concat(".").concat(part2).concat(".").concat(part3);
    }

    private String join(String[] parts) {
        StringBuilder sb = new StringBuilder();

        for(String part : parts) {
            sb.append(part);
        }
        return sb.toString();
    }
}
