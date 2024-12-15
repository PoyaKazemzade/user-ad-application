package se.yrgo.listingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.listingservice.data.AdCopyRepository;
import se.yrgo.listingservice.domain.AdCopy;

import java.util.List;

@Service
public class AdCopyService {
    private final AdCopyRepository data;

    @Autowired
    public AdCopyService(AdCopyRepository data) {
        this.data = data;
    }

    /**
     * Fetch all ads.
     *
     * @return List of all ads.
     */
    public List<AdCopy> getAllAds() {
        return data.findAll();
    }

    /**
     * Fetch ads by category name.
     *
     * @param categoryName the name of the category.
     * @return List of ads in the specified category.
     */
    public List<AdCopy> getAdsByCategory(String categoryName) {
        return data.findByCategoryName(categoryName);
    }

    /**
     * Search for ads based on title query.
     *
     * @param query the search query.
     * @return List of ads matching the search query.
     */
    public List<AdCopy> searchAds(String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Search query was empty.");
        }
        return data.findAll().stream()
                .filter(ad -> ad.getTitle().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}

