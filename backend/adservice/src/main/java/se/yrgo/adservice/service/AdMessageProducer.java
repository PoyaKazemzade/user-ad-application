package se.yrgo.adservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.domain.Ad;

@Service
public class AdMessageProducer {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public AdMessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendAdToQueue(Ad ad) {
        jmsTemplate.convertAndSend("ads.queue", ad);
    }
}

