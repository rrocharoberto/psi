package br.edu.univas.model.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "dateController")
@ViewScoped

/** Este código foi movido para a classe @ConsultarPacienteController */
public class DateController implements Serializable {
	
	private static final long serialVersionUID = 5728262117006678851L;

	private static int CEM_ANOS_ATRAS = -100;
	
	private Date minFirstDate = null;
	private Date minLastDate = new Date();

	public DateController() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, CEM_ANOS_ATRAS);
		minFirstDate = cal.getTime();
	}

//	public void firstDateChoosen(SelectEvent event) {
//		Date date = (Date) event.getObject();

//		minFirstDate = date;

	public void firstDateChoosen() {
		minLastDate = minFirstDate;
		System.out.println("minLastDate updated to: " + minLastDate);
	}
	
	public Date getMinFirstDate() {
		return minFirstDate;
	}
	
	public void setMinFirstDate(Date minFirstDate) {
		this.minFirstDate = minFirstDate;
	}
	
	public Date getMinLastDate() {
		return minLastDate;
	}
	
	public Date getNow() {
		return new Date();
	}

}