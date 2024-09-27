package com.btg.pactual.btg.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fondosActuales")
public class CurrentFunds {

	@Id
	private String fondoId;
	private String nombreFondo;
	private LocalDateTime fechaVinculacion;
	private double montoInvertido;
		
	public CurrentFunds() {
	}
	
	public CurrentFunds(String fondoId, String nombreFondo, LocalDateTime fechaVinculacion, double montoInvertido) {
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
	public LocalDateTime getFechaVinculacion() {
		return fechaVinculacion;
	}
	public void setFechaVinculacion(LocalDateTime fechaVinculacion) {
		this.fechaVinculacion = fechaVinculacion;
	}
	public double getMontoInvertido() {
		return montoInvertido;
	}
	public void setMontoInvertido(double montoInvertido) {
		this.montoInvertido = montoInvertido;
	}
	
}
