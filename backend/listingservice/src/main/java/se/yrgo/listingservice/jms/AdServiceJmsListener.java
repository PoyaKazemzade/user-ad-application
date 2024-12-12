package se.yrgo.listingservice.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import se.yrgo.listingservice.data.AdCopyRepository;
import se.yrgo.listingservice.domain.AdCopy;

import java.util.Date;
import java.util.HashMap;

@Service
public class AdServiceJmsListener {
    private final AdCopyRepository adCopyData;

    public AdServiceJmsListener(AdCopyRepository adCopyData) {
        this.adCopyData = adCopyData;
    }

    @JmsListener(destination = "adQueue")
    public void receiveMessage(HashMap<String, String> message) {

        message.forEach((key, value) -> System.out.println(key + ": " + value));

 /*       AdCopy newAdCopy = new AdCopy();

        // need to parse the received message here, dummy code atm
        newAdCopy.setTitle(message);
        newAdCopy.setDescription(message);
        newAdCopy.setCategoryName(message);
        newAdCopy.setPrice(0);
        newAdCopy.setCreatedDate(new Date());

        adCopyData.save(newAdCopy);*/
    }
}
