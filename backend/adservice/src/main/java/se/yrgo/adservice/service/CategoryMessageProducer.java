package se.yrgo.adservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.domain.AdCategory;

import java.util.HashMap;

@Service
public class CategoryMessageProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${queue.category.name}")
    private String categoryQueue;

    @Autowired
    public CategoryMessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendCategoryToQueue(AdCategory category) {
        var categoryMessage = getMappedMessageData(category);
        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.convertAndSend(categoryQueue, categoryMessage);
    }

    private static HashMap<String, String> getMappedMessageData(AdCategory category) {
        HashMap<String, String> categoryMessage = new HashMap<>();
        categoryMessage.put("id", category.getId().toString());
        categoryMessage.put("categoryName", category.getCategoryName());
        return categoryMessage;
    }
}
