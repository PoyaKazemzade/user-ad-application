package se.yrgo.listingservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.listingservice.data.TrendingAdRepository;
import se.yrgo.listingservice.domain.TrendingAdCategory;

import java.util.List;

@Service
public class TrendingAdCategoryService {
    private final TrendingAdRepository data;

    public TrendingAdCategoryService(TrendingAdRepository data) {
        this.data = data;
    }

    /*
     * List top 3 categories with highest adCount, sorted by adCount in descending order
     */
    public List<TrendingAdCategory> getTopThreeAdCategories() {
        return data.findTop3ByOrderByAdCountDesc();
    }
}
