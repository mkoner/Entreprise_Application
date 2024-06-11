package lab.taxservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TaxMessageListener {
    @JmsListener(destination = "taxQueue")
    public void receiveMessage(JMSMessage message) {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("Message received " + message);
    }
    @JmsListener(destination = "taxQueue")
    public void receiveMessage(String message) {
        System.out.println("Message received " + message);
    }
}
