package br.edu.univas.uteis;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.edu.univas.model.entity.Servico;

public abstract class AbstractServicoConverter implements Converter {
	
	public abstract Map<Integer, Servico> getServicosMap();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("ServicoConverter: getAsObject: " + value);
		
		if (value != null && value.trim().length() > 0) {
			try {
				return getServicosMap().get(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro de conversão de serviço", "Não é um código válido: " + value));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("ServicoConverter: getAsString: " + value);

		if (value != null) {
			return String.valueOf(((Servico) value).getCodigoServico());
		} else {
			String errorMessage = "ServicoConverter: value " + value;
			new RuntimeException(errorMessage).printStackTrace();
			return errorMessage;
		}
	}

}
