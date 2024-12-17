package se.yrgo.adservice.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.domain.Ad;

import java.util.HashMap;

@Service
public class AdMessageProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${queue.ad.name}")
    private String adQueue;

    @Autowired
    public AdMessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendAdToQueue(Ad ad) {
        try {
            var newAdMessage = getMappedMessageData(ad);
            jmsTemplate.setDeliveryPersistent(true);
            jmsTemplate.convertAndSend(adQueue, newAdMessage);
            System.out.println("Ad sent to queue: " + ad.getTitle());
        } catch (JmsException e) {
            System.err.println("Failed to send Ad to queue: " + e.getMessage());
        }
    }

    private static HashMap<String, String> getMappedMessageData(Ad newAd) {
        HashMap<String, String> newAdMessage = new HashMap<>();
        newAdMessage.put("id", newAd.getId().toString());
        newAdMessage.put("userName", newAd.getUserName());
        newAdMessage.put("categoryId", newAd.getCategory().getId().toString());
        newAdMessage.put("categoryName", newAd.getCategory().getCategoryName());
        newAdMessage.put("title", newAd.getTitle());
        newAdMessage.put("description", newAd.getDescription());
        newAdMessage.put("created", newAd.getCreated().toString());
        newAdMessage.put("price", newAd.getPrice().toString());
        return newAdMessage;
    }
}

