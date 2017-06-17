package br.edu.univas.uteis;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.edu.univas.model.entity.Estagiario;

public abstract class AbstractEstagiarioConverter implements Converter {

	public abstract Map<Long, Estagiario> getEstagiariosMap();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("EstagiarioConverter: getAsObject: " + value);
		
		if (value != null && value.trim().length() > 0) {
			try {
				return getEstagiariosMap().get(Long.parseLong(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro de conversão de estagiário", "Não é um CPF válido: " + value));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("EstagiarioConverter: getAsString: " + value);

		if (value != null) {
			return ((Estagiario) value).getMatricula();
		} else {
			String errorMessage = "EstagiarioConverter: value " + value;
			new RuntimeException(errorMessage).printStackTrace();
			return errorMessage;
		}
	}

}
