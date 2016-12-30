package br.edu.univas.example.uteis;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//look at: http://pt.stackoverflow.com/questions/59444/primefaces-datatable-formata%C3%A7%C3%A3o-de-n%C3%BAmeros
@FacesConverter("CPFConverter")
public class CPFConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String[] parts = value.split("-\\.");
        return Integer.parseInt(join(parts));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String valueAsString = value.toString();
        
        int len = valueAsString.length();
        String part1 = valueAsString.substring(0, len - 8);
        String part2 = valueAsString.substring(len - 8, len - 5);
        String part3 = valueAsString.substring(len - 5, len - 2);
        String part4 = valueAsString.substring(len - 2, len);
        
        return formatCPF(part1, part2, part3, part4);
    }

    private String formatCPF(String part1, String part2, String part3, String part4) {
        return part1.concat(".").concat(part2).concat(".").concat(part3).concat("-").concat(part4);
    }

    private String join(String[] parts) {
        StringBuilder sb = new StringBuilder();

        for(String part : parts) {
            sb.append(part);
        }
        return sb.toString();
    }
}
