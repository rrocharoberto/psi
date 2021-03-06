package br.edu.univas.uteis;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.edu.univas.model.entity.Paciente;

public abstract class AbstractPacienteConverter implements Converter {

	public abstract Map<String, Paciente> getPacientesMap();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String numeroProntuario) {
		System.out.println("PacienteConverter: getAsObject: " + numeroProntuario);
		
		if (numeroProntuario != null && numeroProntuario.trim().length() > 0) {
//			try {
				return getPacientesMap().get(numeroProntuario);
//			} catch (NumberFormatException e) {
//				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
//						"Erro de conversão de paciente", "Não é um número válido: " + numeroProntuario));
//			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("PacienteConverter: getAsString: " + value);

		if (value != null) {
			return String.valueOf(((Paciente) value).getDadosPessoais().getCpf());
		} else {
			String errorMessage = "PacienteConverter: value " + value;
			new RuntimeException(errorMessage).printStackTrace();
			return errorMessage;
		}
	}

}
