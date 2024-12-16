package se.yrgo.listingservice.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import se.yrgo.listingservice.data.AdCopyRepository;
import se.yrgo.listingservice.data.TrendingAdRepository;
import se.yrgo.listingservice.domain.AdCopy;
import se.yrgo.listingservice.domain.TrendingAdCategory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Optional;

@Service
public class AdEventJmsListener {
    private final AdCopyRepository adCopyData;
    private final TrendingAdRepository trendingAdData;
    private static final String DEFAULT_DATE = "1970-01-01T00:00:00";

    public AdEventJmsListener(AdCopyRepository adCopyData, TrendingAdRepository trendingAdData) {
        this.adCopyData = adCopyData;
        this.trendingAdData = trendingAdData;
    }

    @JmsListener(destination = "adQueue")
    public void receiveAdCreatedMessage(Map<String, String> message) {
        message.forEach((key, value) -> System.out.println(key + ": " + value));

        AdCopy newAdCopy = new AdCopy();

        // parse the received message here
        newAdCopy.setTitle(message.get("title"));
        newAdCopy.setDescription(message.get("description"));
        newAdCopy.setCategoryName(message.get("categoryName"));
        newAdCopy.setCategoryId(Integer.parseInt(message.get("categoryId")));
        newAdCopy.setPrice(Integer.parseInt(message.get("price")));

        // parse LocalDateTime from String
        String dateString = message.get("created");
        if (dateString != null && !dateString.isEmpty()) {
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
                newAdCopy.setCreated(parsedDate);
            } catch (DateTimeParseException ex) {
                ex.printStackTrace();
                newAdCopy.setCreated(LocalDateTime.parse(DEFAULT_DATE, DateTimeFormatter.ISO_DATE_TIME));
            }
        } else {
            newAdCopy.setCreated(LocalDateTime.parse(DEFAULT_DATE, DateTimeFormatter.ISO_DATE_TIME));
        }

        adCopyData.save(newAdCopy);
        incrementTrendingAdCategory(newAdCopy);

    }

    @JmsListener(destination = "deleteAdQueue")
    public void receiveDeleteAdMessage(String message) {
        var adId = Integer.parseInt(message);
        var adToDelete = adCopyData.findById(adId);
        adToDelete.ifPresent(adCopyData::delete);
        decrementTrendingAdCategory(adToDelete);
    }

    private void incrementTrendingAdCategory(AdCopy newAdCopy) {
        // Handle the trending ad categories
        trendingAdData.findByCategoryName(newAdCopy.getCategoryName())
                .ifPresentOrElse(category -> {
                    category.setAdCount(category.getAdCount() + 1);
                    trendingAdData.save(category);
                }, () -> {
                    TrendingAdCategory newTrendingCategory = new TrendingAdCategory();
                    newTrendingCategory.setId(newAdCopy.getCategoryId());
                    newTrendingCategory.setCategoryName(newAdCopy.getCategoryName());
                    newTrendingCategory.setAdCount(1);
                    trendingAdData.save(newTrendingCategory);
                });
    }

    private void decrementTrendingAdCategory(Optional<AdCopy> adCopyToDelete) {
        adCopyToDelete.ifPresent(adCopy ->
                trendingAdData.findByCategoryName(adCopy.getCategoryName())
                        .ifPresentOrElse(category -> {
                            long updatedCount = category.getAdCount() - 1;

                            if (updatedCount <= 0) {
                                trendingAdData.delete(category);
                            } else {
                                category.setAdCount(updatedCount);
                                trendingAdData.save(category);
                            }
                        }, () -> {
                            throw new IllegalArgumentException("TrendingAdCategory not found for categoryName: "
                                    + adCopy.getCategoryName());
                        })
        );
    }
}
