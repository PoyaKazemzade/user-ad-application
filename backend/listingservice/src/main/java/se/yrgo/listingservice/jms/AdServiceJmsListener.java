package se.yrgo.listingservice.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import se.yrgo.listingservice.data.AdCopyRepository;
import se.yrgo.listingservice.domain.AdCopy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class AdServiceJmsListener {
    private final AdCopyRepository adCopyData;
    private static final String DEFAULT_DATE = "0000-01-01T00:00:00";

    public AdServiceJmsListener(AdCopyRepository adCopyData) {
        this.adCopyData = adCopyData;
    }

    @JmsListener(destination = "adQueue")
    public void receiveMessage(Map<String, String> message) {
        message.forEach((key, value) -> System.out.println(key + ": " + value));

        AdCopy newAdCopy = new AdCopy();

        // parse the received message here, create set values to AdCopy instance
        newAdCopy.setTitle(message.get("title"));
        newAdCopy.setDescription(message.get("description"));
        newAdCopy.setCategoryName(message.get("categoryName"));
        newAdCopy.setPrice(Integer.parseInt(message.get("price")));

        // parse date from String
        String dateString = message.get("created");
        if (dateString != null && !dateString.isEmpty()) {
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
                newAdCopy.setCreated(parsedDate);
            } catch (Exception ex) {
                ex.printStackTrace();
                newAdCopy.setCreated(LocalDateTime.parse(DEFAULT_DATE, DateTimeFormatter.ISO_DATE_TIME));
            }
        } else {
            newAdCopy.setCreated(LocalDateTime.parse(DEFAULT_DATE, DateTimeFormatter.ISO_DATE_TIME));
        }

        adCopyData.save(newAdCopy);
    }
}
