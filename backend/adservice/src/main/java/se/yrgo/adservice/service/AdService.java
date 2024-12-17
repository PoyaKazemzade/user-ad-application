package se.yrgo.adservice.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import se.yrgo.adservice.data.AdCategoryRepository;
import se.yrgo.adservice.data.AdRepository;
import se.yrgo.adservice.domain.Ad;
import se.yrgo.adservice.domain.AdCategory;
import se.yrgo.adservice.dto.AdDto;
import se.yrgo.adservice.dto.AdResponseDto;
import se.yrgo.adservice.jms.AdMessageProducer;
import se.yrgo.adservice.jms.DeleteAdMessageProducer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdService {
    private final RestTemplate restTemplate;
    private final AdRepository adRepository;
    private final AdCategoryRepository adCategoryRepository;
    private final AdMessageProducer adMessageProducer;
    private final DeleteAdMessageProducer deleteAdMessageProducer;

    @Autowired
    public AdService(AdRepository adRepository, AdCategoryRepository adCategoryRepository, AdMessageProducer adMessageProducer, DeleteAdMessageProducer deleteAdMessageProducer, RestTemplate restTemplate) {
        this.adRepository = adRepository;
        this.adCategoryRepository = adCategoryRepository;
        this.adMessageProducer = adMessageProducer;
        this.deleteAdMessageProducer = deleteAdMessageProducer;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void initializeAds() {
        if (adRepository.count() == 0) {
            List<AdDto> ads = List.of(
                    new AdDto("John Doe", "Gaming Laptop for Sale", "High-performance gaming laptop with RTX 3080 graphics card.", 1200, 1),
                    new AdDto("Jane Smith", "iPhone 13 Pro Max", "Brand new iPhone 13 Pro Max, 128GB, space gray.", 999, 2),
                    new AdDto("John Doe", "Used Toyota Corolla", "2015 Toyota Corolla in excellent condition with low mileage.", 7000, 3),
                    new AdDto("Jane Smith", "Winter Jacket for Women", "Stylish and warm winter jacket, size M.", 85, 4),
                    new AdDto("John Doe", "Golden Retriever Puppies", "Adorable golden retriever puppies looking for a new home.", 300, 5),
                    new AdDto("Jane Smith", "Sofa Set - Like New", "6-seater comfortable sofa set, barely used.", 450, 6),
                    new AdDto("John Doe", "PlayStation 5 Console", "PS5 console with two controllers and three games.", 550, 1),
                    new AdDto("Jane Smith", "Samsung Galaxy S22 Ultra", "Brand new Samsung Galaxy S22 Ultra, 256GB storage.", 1100, 2),
                    new AdDto("John Doe", "Honda Civic 2018", "2018 Honda Civic in excellent condition, fully serviced.", 8500, 3),
                    new AdDto("Jane Smith", "Dining Table Set", "Wooden dining table set for 4 people, includes chairs.", 200, 6)
            );
            for (AdDto ad : ads) {
                createAd(ad);
            }
        } else {
            System.out.println("There are ads in database");
        }
    }

    public AdResponseDto getAdById(Integer id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ad not found"));
        return new AdResponseDto(ad);
    }

    public Ad createAd(AdDto adDto) {
        boolean userExists = checkIfUserExists(adDto.getUserName());
        if (!userExists) {
            throw new IllegalArgumentException("User does not exist in the external system");
        }

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
        try {
            deleteAdMessageProducer.sendDeleteAdMessageToQueue(id);
        } catch (Exception e) {
            System.err.println("Failed to send delete message to the queue: " + e.getMessage());
        }
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

    private boolean checkIfUserExists(String userName) {
        String url = "http://localhost:8085/api/v1/users/name/" + userName;
        try {
            // Perform a GET request to the external service
            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);

            // Return true if the response status is 200 OK
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException e) {
            // Check if the error is 404 Not Found
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            }
            // For other errors, rethrow or handle as needed
            throw new RuntimeException("Error while checking user existence: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle unexpected exceptions
            throw new RuntimeException("Unexpected error while checking user existence: " + e.getMessage(), e);
        }
    }

    //to be deleted
//    public List<AdResponseDto> getAllAds() {
//        List<Ad> allAdsInRepository = adRepository.findAll();
//
//        return allAdsInRepository.stream()
//                .map(AdResponseDto::new)
//                .collect(Collectors.toList());
//    }
    //to be deleted
//    public List<AdResponseDto> getAdsByCategory(Integer categoryId) {
//        List<Ad> ads = adRepository.findByCategory_Id(categoryId);
//        return ads.stream()
//                .map(AdResponseDto::new) // Map each Ad to AdResponseDto
//                .collect(Collectors.toList());
//    }

}
