package edu.miu.bank.integration.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSSenderImpl implements JMSSender{
	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendJMSMessage (String text){
		JMSMessage message = new JMSMessage(text);
		ObjectMapper objectMapper = new ObjectMapper();
		String messageAsString;
        try {
			messageAsString  = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
		jmsTemplate.send("taxQueue", session -> session.createTextMessage(messageAsString));
        System.out.println("JMSSender: sending JMS message ="+text);
	}

}
