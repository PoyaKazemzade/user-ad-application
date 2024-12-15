package se.yrgo.listingservice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.listingservice.data.TrendingAdRepository;
import se.yrgo.listingservice.domain.TrendingAdCategory;

import java.util.List;

/**
 * Returns JSON responses when the controller returns objects.
 * Only requests with Accept: application/json will get a valid response.
 */
@RestController
@RequestMapping("api/v1/trending-categories")
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
    @Operation(
            summary = "Get top categories",
            description = "Returns a list of all categories with available ads, sorted in DESC order with most ads populated in them."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of trending categories in JSON format",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TrendingAdCategory.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No top categories available",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<List<TrendingAdCategory>> getTrendingCategories() {
        var res = data.findByOrderByAdCountDesc();
        if (res.isEmpty()) {
            throw new ResourceNotFoundException("No top categories available.");
        }
        return ResponseEntity.ok(res);
    }
}
