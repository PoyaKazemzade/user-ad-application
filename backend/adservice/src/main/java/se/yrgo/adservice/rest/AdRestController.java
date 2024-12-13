package se.yrgo.adservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.yrgo.adservice.domain.Ad;
import se.yrgo.adservice.service.AdMessageProducer;
import se.yrgo.adservice.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ads")
public class AdRestController {

    private final AdService adService;
    private final AdMessageProducer adMessageProducer;

    @Autowired
    public AdRestController(AdService adService, AdMessageProducer adMessageProducer) {
        this.adService = adService;
        this.adMessageProducer = adMessageProducer;
    }

    @GetMapping
    public List<Ad> getAllAds() {
        return adService.getAllAds();
    }

    @GetMapping("/{id}")
    public Ad getAdById(@PathVariable Integer id) {
        return adService.getAdById(id);
    }

    @PostMapping
    public Ad createAd(@RequestBody Ad ad) {
        try {
            Ad newAd = adService.createAd(ad);
            adMessageProducer.sendAdToQueue(newAd);
            return newAd;
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message to queue: " + e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public Ad updateAd(@PathVariable Integer id, @RequestBody Ad ad) {
        ad.setId(id);
        return adService.createAd(ad); // Using createAd here for both update and create simplicity
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
