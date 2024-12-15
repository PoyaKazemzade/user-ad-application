package se.yrgo.adservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.data.AdCategoryRepository;
import se.yrgo.adservice.data.AdRepository;
import se.yrgo.adservice.domain.Ad;
import se.yrgo.adservice.domain.AdCategory;
import se.yrgo.adservice.dto.AdDto;
import se.yrgo.adservice.dto.AdResponseDto;
import se.yrgo.adservice.jms.AdMessageProducer;

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

    public AdResponseDto getAdById(Integer id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ad not found"));
        return new AdResponseDto(ad);
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

        Ad savedAd = adRepository.save(ad);
        try {
            adMessageProducer.sendAdToQueue(savedAd);
        } catch (Exception e) {
            System.err.println("Failed to send Ad to the queue: " + e.getMessage());
        }
        return savedAd;
    }

    public void deleteAd(Integer id) {
        adRepository.deleteById(id);
    }

    public List<Ad> getAdsByCategory(Integer categoryId) {
        return adRepository.findByCategory_Id(categoryId);
    }

    public Ad updateAd(Integer id, AdDto adDto) {
        Ad existingAd = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ad not found"));

        AdCategory category = adCategoryRepository.findById(adDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        existingAd.setUserName(adDto.getUserName());
        existingAd.setTitle(adDto.getTitle());
        existingAd.setDescription(adDto.getDescription());
        existingAd.setPrice(adDto.getPrice());
        existingAd.setCategory(category);

        return adRepository.save(existingAd);
    }

}
