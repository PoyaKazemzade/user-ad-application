package se.yrgo.adservice.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.domain.Ad;

import java.util.HashMap;

@Service
public class DeleteAdMessageProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${queue.delete.name}")
    private String deleteAdQueue;

    @Autowired
    public DeleteAdMessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendDeleteAdMessageToQueue(Integer adId) {
        try {
            jmsTemplate.setDeliveryPersistent(true);
            jmsTemplate.convertAndSend(deleteAdQueue, adId.toString());
            System.out.println("Delete ad message sent to queue");
        } catch (JmsException e) {
            System.err.println("Failed to send message to queue: " + e.getMessage());
        }
    }

}


