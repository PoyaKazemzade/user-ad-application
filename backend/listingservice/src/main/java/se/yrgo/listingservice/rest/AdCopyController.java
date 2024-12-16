package se.yrgo.listingservice.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.listingservice.domain.AdCopy;
import se.yrgo.listingservice.domain.TrendingAdCategory;
import se.yrgo.listingservice.service.AdCopyService;

import java.util.List;

/**
 * Returns JSON responses when the controller returns objects.
 * Only requests with Accept: application/json will get a valid response.
 */
@RestController
@RequestMapping("api/v1/ads")
public class AdCopyController {
    private final AdCopyService adCopyService;

    public AdCopyController(AdCopyService adCopyService) {
        this.adCopyService = adCopyService;
    }

    /**
     * GET /ads
     *
     * @return a list of all ads
     */
    @GetMapping("/")
    @Operation(
            summary = "Get all available ads.",
            description = "Returns a list of all available ads."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of all ads in JSON format",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AdCopy.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No ads available",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<List<AdCopy>> getAllAds() {
        var res = adCopyService.getAllAds();
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
    @GetMapping(value = "/", params = {"filter"})
    @Operation(
            summary = "Get all ads filtered on ad category.",
            description = "Returns a list of all ads in the specific category."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of all ads in the specific category, in JSON format",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AdCopy.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No ads available in the category",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<List<AdCopy>> getAdsForCategory(
            @Parameter(description = "Category name to filter ads by", required = true)
            @RequestParam("filter") String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            throw new IllegalArgumentException("Invalid category name.");
        }
        var res = adCopyService.getAdsByCategory(categoryName);
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
    @GetMapping(value = "/search", params = {"q"})
    @Operation(
            summary = "Get ads with title matching your search term.",
            description = "Returns a list of ads matching search query. A matching result will be an ad that contains the search term in its title."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of ads for matching search result, in JSON format",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TrendingAdCategory.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Empty or bad query",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No matching search result found",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<List<AdCopy>> searchAds(
            @Parameter(description = "Search term to filter ads by", required = true)
            @RequestParam(value = "q", required = false) String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Search query was empty.");
        }
        var res = adCopyService.searchAds(query);
        if (res.isEmpty()) {
            throw new ResourceNotFoundException("No matching result found from search for: " + query);
        }
        return ResponseEntity.ok(res);
    }
}
