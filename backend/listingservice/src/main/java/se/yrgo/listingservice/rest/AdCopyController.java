package se.yrgo.listingservice.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.listingservice.data.AdCopyRepository;
import se.yrgo.listingservice.domain.AdCopy;

import java.util.List;

/**
 * Returns JSON responses when the controller returns objects
 */
@RestController
@RequestMapping("api/v1/ads")
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
    public ResponseEntity<List<AdCopy>> getAllAds() {
        var res = data.findAll();
        if (res.isEmpty()) {
            throw new ResourceNotFoundException("No ads available.");
        }
        return ResponseEntity.ok(res);
    }

    /**
     * GET /ads?filter=category
     *
     * @param categoryName name of the category
     * @return a list of ads in the category
     */
    @GetMapping(params = {"filter"})
    public ResponseEntity<List<AdCopy>> getAdsForCategory(@RequestParam("filter") String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            throw new IllegalArgumentException("Invalid category name.");
        }
        var res = data.findByCategoryName(categoryName);
        if (res.isEmpty()) {
            throw new ResourceNotFoundException("No ads found for category: " + categoryName);
        }
        return ResponseEntity.ok(res);
    }

    /**
     * GET /ads/search?q=term - Search ads based on keywords
     *
     * @param query the search term
     * @return result
     */
    @GetMapping("/search")
    public ResponseEntity<List<AdCopy>> searchAds(@RequestParam(value = "q", required = false) String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Search query was empty.");
        }
        var res = data.findAll().stream()
                .filter(ad -> ad.getTitle().toLowerCase().contains(query.toLowerCase()))
                .toList();
        if (res.isEmpty()) {
            throw new ResourceNotFoundException("No matching result found from search for: " + query);
        }
        return ResponseEntity.ok(res);
    }
}
