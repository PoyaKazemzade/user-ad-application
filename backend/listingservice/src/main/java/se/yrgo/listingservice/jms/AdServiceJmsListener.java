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
        String dateString = message.get("createdDate");
        if (dateString != null && !dateString.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // ISO format used in toString()
                LocalDateTime parsedDate = LocalDateTime.parse(dateString, formatter);
                newAdCopy.setCreated(parsedDate); // Set the parsed date
            } catch (Exception ex) {
                ex.printStackTrace();
                newAdCopy.setCreated(LocalDateTime.now()); // Fallback to current date if parsing fails
            }
        } else {
            newAdCopy.setCreated(LocalDateTime.now()); // Set to current date if createDate is missing
        }

        adCopyData.save(newAdCopy);
    }
}
