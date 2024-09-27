package com.btg.pactual.btg.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SmsSender {

    @Value("${account.sid}")
    private String accountSid;

    @Value("${auth.token}")
    private String authToken;

	public void notification(String text, String phone) {
		Twilio.init(accountSid, authToken);

		Message message = Message.creator(new PhoneNumber("+57" + phone), // El número de destino
				new PhoneNumber("+14153606973"), // Tu número de Twilio
				text).create();

		System.out.println("SMS sent: " + message.getSid());
	}
}
