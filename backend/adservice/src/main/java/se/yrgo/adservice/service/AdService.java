package se.yrgo.adservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.data.AdCategoryRepository;
import se.yrgo.adservice.data.AdRepository;
import se.yrgo.adservice.domain.Ad;
import se.yrgo.adservice.domain.AdCategory;
import se.yrgo.adservice.dto.AdDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {
    private final AdRepository adRepository;
    private final AdCategoryRepository adCategoryRepository;
    private final AdMessageProducer adMessageProducer;

    @Autowired
    public AdService(AdRepository adRepository, AdCategoryRepository adCategoryRepository, AdMessageProducer adMessageProducer) {
        this.adRepository = adRepository;
        this.adCategoryRepository = adCategoryRepository;
        this.adMessageProducer = adMessageProducer;
    }

    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    public Ad getAdById(Integer id) {
        return adRepository.findById(id).orElseThrow(() -> new RuntimeException("Ad not found"));
    }

    public Ad createAd(AdDto adDto) {
        AdCategory category = adCategoryRepository.findById(adDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Ad ad = new Ad();
        ad.setUserName(adDto.getUserName());
        ad.setTitle(adDto.getTitle());
        ad.setDescription(adDto.getDescription());
        ad.setPrice(adDto.getPrice());
        ad.setCategory(category);
        return adRepository.save(ad);
        //adMessageProducer.sendAdToQueue(ad);
    }

    public void deleteAd(Integer id) {
        adRepository.deleteById(id);
    }

    public List<Ad> getAdsByCategory(Integer categoryId) {
        return adRepository.findByCategory_Id(categoryId);
    }
}
