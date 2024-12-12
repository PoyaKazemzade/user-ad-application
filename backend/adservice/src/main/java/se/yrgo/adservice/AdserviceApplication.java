package se.yrgo.adservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import se.yrgo.adservice.domain.Ad;
import se.yrgo.adservice.domain.AdCategory;

import java.util.Date;
import java.util.HashMap;

@SpringBootApplication
public class AdserviceApplication {

    public static void main(String[] args) {
        //SpringApplication.run(AdserviceApplication.class, args);

        ApplicationContext ctx = SpringApplication.run(AdserviceApplication.class, args);
        JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);

        Ad newAd = new Ad(
                Integer.valueOf(10),
                "user123",
                new AdCategory(1, "food"),
                "Used Car for Sale",
                "This is a description of a used car for sale",
                new Date("2014/01/20"),
                Integer.valueOf(49000)
        );
        HashMap<String, String> newAdMessage = new HashMap<>();
        newAdMessage.put("userName", newAd.getUserName());
        newAdMessage.put("categoryId", newAd.getCategory().getId().toString());
        newAdMessage.put("categoryName", newAd.getCategory().getCategoryName());
        newAdMessage.put("createDate", newAd.getCreateDate().toString());
        newAdMessage.put("price", newAd.getPrice().toString());

        jmsTemplate.convertAndSend("adQueue", newAdMessage);
    }

}
