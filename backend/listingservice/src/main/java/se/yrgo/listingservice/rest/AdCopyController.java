package se.yrgo.listingservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.listingservice.data.AdCopyRepository;
import se.yrgo.listingservice.domain.AdCopy;

import java.util.List;

/**
 * Returns JSON responses when the controller returns objects
 * TODO: Additional error handling can be added for cases like invalid parameters or empty results.
 */
@RestController
@RequestMapping("/ads")
public class AdCopyController {
    private final AdCopyRepository data;

    public AdCopyController(AdCopyRepository data) {
        this.data = data;
    }

    /**
     * GET /ads
     *
     * @return a list of all ads
     */
    @GetMapping
    public List<AdCopy> getAllAds() {
        return data.findAll();
    }

    /**
     * GET /ads?filter=category
     *
     * @param categoryName name of the category
     * @return a list of ads in the category
     */
    @GetMapping(params = {"filter"})
    public List<AdCopy> getAdsForCategory(@RequestParam("filter") String categoryName) {
        return data.findByCategoryName(categoryName).stream().toList();
    }

    /**
     * GET /ads/search?q=term - Search ads based on keywords
     *
     * @param query the search term
     * @return result
     */
    @GetMapping("/search")
    public List<AdCopy> searchAds(@RequestParam("q") String query) {
        return data.findAll().stream()
                .filter(ad -> ad.getTitle().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

}
