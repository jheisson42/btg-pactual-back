package com.btg.pactual.btg.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class User {

	@Id
	private String id;
	private String nombre;
	private double saldo;
	private String email;
	private String telefono;
	private List<CurrentFunds>  currentFunds;
	private List<Transaction> historyTransactions;

	 public User() {
	    }

	    // Constructor completo
	    public User(String id, String nombre, double saldo, String email, String telefono,
	                      List<CurrentFunds> currentFunds, List<Transaction> historyTransactions) {
	        this.id = id;
	        this.nombre = nombre;
	        this.saldo = saldo;
	        this.email = email;
	        this.telefono = telefono;
	        this.currentFunds = currentFunds;
	        this.historyTransactions = historyTransactions;
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

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<CurrentFunds> getCurrentFunds() {
		return currentFunds;
	}

	public void setCurrentFunds(List<CurrentFunds> currentFunds) {
		this.currentFunds = currentFunds;
	}

	public List<Transaction> getHistoryTransactions() {
		return historyTransactions;
	}

	public void setHistoryTransactions(List<Transaction> historyTransactions) {
		this.historyTransactions = historyTransactions;
	}
	
}
