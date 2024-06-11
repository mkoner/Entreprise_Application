package edu.miu.bank.integration.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JMSSenderImpl implements JMSSender{
//	@Autowired
//	JmsTemplate jmsTemplate;
	
	public void sendJMSMessage (String text){
//		JMSMessage message = new JMSMessage(text);
//		//jmsTemplate.convertAndSend("taxQueue",message);
//		jmsTemplate.convertAndSend("taxQueue",text);
		System.out.println("Sending message: "+ text);
	}

}
