package se.yrgo.listingservice.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.listingservice.data.TrendingAdRepository;
import se.yrgo.listingservice.domain.TrendingAdCategory;

import java.util.List;

/**
 * Returns JSON responses when the controller returns objects
 */
@RestController
@RequestMapping("/trending-categories")
public class TrendingAdCategoryController {
    private final TrendingAdRepository data;

    public TrendingAdCategoryController(TrendingAdRepository data) {
        this.data = data;
    }

    /**
     * GET /trending-categories
     * List all trending categories sorted by adCount in descending order
     *
     * @return result
     */
    @GetMapping
    public ResponseEntity<List<TrendingAdCategory>> getTrendingCategories() {
        var res = data.findByOrderByAdCountDesc();
        if (res.isEmpty()) {
            throw new ResourceNotFoundException("No top categories available.");
        }
        return ResponseEntity.ok(res);
    }
}
