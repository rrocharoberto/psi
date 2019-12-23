package br.edu.univas.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


/**
 * The persistent class for the documento database table.
 * 
 */
@Entity
@Table(name="DOCUMENTO")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String numeroProntuario;
	
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] declaracaoContent;

	@Lob	
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] termoContent;

	public Documento() {
	}

	public String getNumeroProntuario() {
		return this.numeroProntuario;
	}

	public void setNumeroProntuario(String numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}

	public byte[] getDeclaracaoContent() {
		return this.declaracaoContent;
	}

	public void setDeclaracaoContent(byte[] declaracaoContent) {
		this.declaracaoContent = declaracaoContent;
	}

	public byte[] getTermoContent() {
		return this.termoContent;
	}

	public void setTermoContent(byte[] termoContent) {
		this.termoContent = termoContent;
	}

}