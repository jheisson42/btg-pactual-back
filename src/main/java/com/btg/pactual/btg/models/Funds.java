package com.btg.pactual.btg.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fondos")
public class Funds {

    @Id
    private String id;
    private String nombre;
    private Double montoMinimoVinculacion;
    private String categoria; 
    
	public Funds() {
	}
	
	public Funds(String id, String nombre, Double montoMinimoVinculacion, String categoria) {
		this.id = id;
		this.nombre = nombre;
		this.montoMinimoVinculacion = montoMinimoVinculacion;
		this.categoria = categoria;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getMontoMinimoVinculacion() {
		return montoMinimoVinculacion;
	}

	public void setMontoMinimoVinculacion(Double montoMinimoVinculacion) {
		this.montoMinimoVinculacion = montoMinimoVinculacion;
	}

	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
    
    
    		
}
