package br.edu.univas.example.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univas.model.controller.AcompanhaController;
import br.edu.univas.model.entity.Estagiario;

@Named(value = "EstagiarioConverter")
@SessionScoped
public class EstagiarioConverter implements Converter {

	@Inject
	AcompanhaController acompanhaController;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("EstagiarioConverter: getAsObject: " + value);
		
		if (value != null && value.trim().length() > 0) {
			try {
				return acompanhaController.getEstagiariosMap().get(Long.parseLong(value));
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
			return String.valueOf(((Estagiario) value).getCpf());
		} else {
			String errorMessage = "EstagiarioConverter: value " + value;
			new RuntimeException(errorMessage).printStackTrace();
			return errorMessage;
		}
	}

}
