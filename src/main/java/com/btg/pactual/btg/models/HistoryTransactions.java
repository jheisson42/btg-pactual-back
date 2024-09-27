package com.btg.pactual.btg.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "historicoTransacciones")
public class HistoryTransactions {

	@Id
	private String transationId;
    private String type;
    private String nameFund;
    private Date date;
    private Double amount;
    
	public String getTransationId() {
		return transationId;
	}
	public void setTransationId(String transationId) {
		this.transationId = transationId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNameFund() {
		return nameFund;
	}
	public void setNameFund(String nameFund) {
		this.nameFund = nameFund;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
    
    
}
