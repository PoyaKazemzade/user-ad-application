package se.yrgo.adservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.data.AdCategoryRepository;
import se.yrgo.adservice.data.AdRepository;
import se.yrgo.adservice.domain.Ad;

import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final AdCategoryRepository adCategoryRepository;

    @Autowired
    public AdService(AdRepository adRepository, AdCategoryRepository adCategoryRepository) {
        this.adRepository = adRepository;
        this.adCategoryRepository = adCategoryRepository;
    }

    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    public Ad getAdById(Integer id) {
        return adRepository.findById(id).orElseThrow(() -> new RuntimeException("Ad not found"));
    }

    public Ad createAd(Ad ad) {
        return adRepository.save(ad);
    }

    public void deleteAd(Integer id) {
        adRepository.deleteById(id);
    }

    public List<Ad> getAdsByCategory(Integer categoryId) {
        return adRepository.findByCategory_Id(categoryId);
    }
}
