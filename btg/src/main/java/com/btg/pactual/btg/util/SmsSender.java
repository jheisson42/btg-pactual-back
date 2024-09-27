package com.btg.pactual.btg.util;

import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SmsSender {

	public static final String ACCOUNT_SID = "AC1fc420384884a91143d43fa6134e0c49";
	public static final String AUTH_TOKEN = "910f727a20fbac1dfd8c702a4b5993fa";

	public void notification(String text, String phone) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(new PhoneNumber("+57" + phone), // El número de destino
				new PhoneNumber("+14153606973"), // Tu número de Twilio
				text).create();

		System.out.println("SMS sent: " + message.getSid());
	}
}
