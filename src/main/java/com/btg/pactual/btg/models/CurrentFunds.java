package com.btg.pactual.btg.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fondosActuales")
public class CurrentFunds {

	@Id
	private String fondoId;
	private String nombreFondo;
	private Date fechaVinculacion;
	private double montoInvertido;
		
	public CurrentFunds() {
	}
	
	public CurrentFunds(String fondoId, String nombreFondo, Date fechaVinculacion, double montoInvertido) {
		this.fondoId = fondoId;
		this.nombreFondo = nombreFondo;
		this.fechaVinculacion = fechaVinculacion;
		this.montoInvertido = montoInvertido;
	}
	public String getFondoId() {
		return fondoId;
	}
	public void setFondoId(String fondoId) {
		this.fondoId = fondoId;
	}
	public String getNombreFondo() {
		return nombreFondo;
	}
	public void setNombreFondo(String nombreFondo) {	
		this.nombreFondo = nombreFondo;
	}
	public Date getFechaVinculacion() {
		return fechaVinculacion;
	}
	public void setFechaVinculacion(Date fechaVinculacion) {
		this.fechaVinculacion = fechaVinculacion;
	}
	public double getMontoInvertido() {
		return montoInvertido;
	}
	public void setMontoInvertido(double montoInvertido) {
		this.montoInvertido = montoInvertido;
	}
	
}
