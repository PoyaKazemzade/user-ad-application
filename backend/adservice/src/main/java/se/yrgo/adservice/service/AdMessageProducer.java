package se.yrgo.adservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.domain.Ad;

import java.util.HashMap;

@Service
public class AdMessageProducer {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public AdMessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendAdToQueue(Ad ad) {
        var newAdMessage = getMappedMessageData(ad);

        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.convertAndSend("adQueue", newAdMessage);
    }

    private static HashMap<String, String> getMappedMessageData(Ad newAd) {
        HashMap<String, String> newAdMessage = new HashMap<>();
        newAdMessage.put("userName", newAd.getUserName());
        newAdMessage.put("categoryId", newAd.getCategory().getId().toString());
        newAdMessage.put("categoryName", newAd.getCategory().getCategoryName());
        newAdMessage.put("title", newAd.getTitle());
        newAdMessage.put("description", newAd.getDescription());
        newAdMessage.put("createDate", newAd.getCreated().toString());
        newAdMessage.put("price", newAd.getPrice().toString());
        return newAdMessage;
    }
}

