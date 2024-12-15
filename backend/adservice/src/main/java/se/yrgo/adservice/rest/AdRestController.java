package se.yrgo.adservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.yrgo.adservice.domain.Ad;
import se.yrgo.adservice.dto.AdDto;
import se.yrgo.adservice.dto.AdResponseDto;
import se.yrgo.adservice.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ads")
public class AdRestController {

    private final AdService adService;

    @Autowired
    public AdRestController(AdService adService) {
        this.adService = adService;
    }

//    @GetMapping
//    public List<AdResponseDto> getAllAds() {
//        return adService.getAllAds();
//    }

//
//    @PostMapping
//    public AdResponseDto createAd(@RequestBody Ad ad) {
//        return adService.createAd(ad); // Ensure createAd also maps to AdResponseDto
//    }
    @GetMapping
    public List<Ad> getAllAds() {
        return adService.getAllAds();
    }

    @GetMapping("/{id}")
    public AdResponseDto getAdById(@PathVariable Integer id) {
        return adService.getAdById(id);
    }

    // Create a new ad
    @PostMapping
    public Ad createAd(@RequestBody AdDto adDto) {
        return adService.createAd(adDto);
    }

    // Update an existing ad
    @PutMapping("/{id}")
    public Ad updateAd(@PathVariable Integer id, @RequestBody AdDto adDto) {
        return adService.updateAd(id, adDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAd(@PathVariable Integer id) {
        adService.deleteAd(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Ad> getAdsByCategory(@PathVariable Integer categoryId) {
        return adService.getAdsByCategory(categoryId);
    }
}
