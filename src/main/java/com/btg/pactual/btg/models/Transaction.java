package com.btg.pactual.btg.models;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transacciones") // Define que esta clase representa un documento en MongoDB
public class Transaction {

	private String transaccionId;
    private String tipo; // "apertura" o "cancelacion"
    private String nombreFondo;
    private Date fecha;
    private double monto;

    // Constructor vacío
    public Transaction() {
    }

    // Constructor completo
    public Transaction(String transaccionId, String tipo, String nombreFondo, Date fecha, double monto) {
        this.transaccionId = transaccionId;
        this.tipo = tipo;
        this.nombreFondo = nombreFondo;
        this.fecha = fecha;
        this.monto = monto;
    }

    // Getters y Setters
    public String getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(String transaccionId) {
        this.transaccionId = transaccionId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreFondo() {
        return nombreFondo;
    }

    public void setNombreFondo(String nombreFondo) {
        this.nombreFondo = nombreFondo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
