package com.btg.pactual.btg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btg.pactual.btg.dto.SubscriptionRequest;
import com.btg.pactual.btg.models.Funds;
import com.btg.pactual.btg.service.interfaces.IFundsService;

@CrossOrigin
@RestController
@RequestMapping("funds")
public class FundsController {

	private final IFundsService fundsService;
	
	public FundsController(IFundsService fundsService){
		this.fundsService = fundsService;
	}

	@PostMapping("/manage-subscription")
	public ResponseEntity<Map<String, String>> subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
	    Map<String, String> response = new HashMap<>();
	    try {
	        fundsService.manageSubscription(subscriptionRequest.getUsuarioId(), subscriptionRequest.getFondoId(), subscriptionRequest.getAction());
	        response.put("message", "Subscripcion " + subscriptionRequest.getAction());
	        return new ResponseEntity<>(response, HttpStatus.OK);    
	    } catch (Exception e) {
	        response.put("error", e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.CONFLICT);    
	    }
	}


    // Obtener todos los fondos
    @GetMapping("/get-all-funds")
    public List<Funds> getFunds() {
        return fundsService.getFunds();
    }
}
