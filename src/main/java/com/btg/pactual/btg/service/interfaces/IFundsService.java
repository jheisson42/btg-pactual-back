package com.btg.pactual.btg.service.interfaces;

import java.util.List;

import com.btg.pactual.btg.models.Funds;

public interface IFundsService {
	
	String manageSubscription(String usuarioId, String fondoId, String action) throws Exception;
	List<Funds> getFunds();
}
