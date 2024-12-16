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

    @GetMapping("/{id}")
    public AdResponseDto getAdById(@PathVariable Integer id) {
        return adService.getAdById(id);
    }

    @PostMapping
    public Ad createAd(@RequestBody AdDto adDto) {
        return adService.createAd(adDto);
    }

    @PutMapping("/{id}")
    public Ad updateAd(@PathVariable Integer id, @RequestBody AdDto adDto) {
        return adService.updateAd(id, adDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAd(@PathVariable Integer id) {
        adService.deleteAd(id);
    }

//    to be deleted
//    @GetMapping
//    public List<AdResponseDto> getAllAds() {
//        return adService.getAllAds();
//    }
//    to be deleted
//    @GetMapping("/category/{categoryId}")
//    public List<AdResponseDto> getAdsByCategory(@PathVariable Integer categoryId) {
//        return adService.getAdsByCategory(categoryId);
//    }

}
