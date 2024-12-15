package se.yrgo.adservice.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.data.AdCategoryRepository;
import se.yrgo.adservice.domain.AdCategory;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdCategoryService {

    private final AdCategoryRepository adCategoryRepository;

    @Autowired
    public AdCategoryService(AdCategoryRepository adCategoryRepository) {
        this.adCategoryRepository = adCategoryRepository;
    }

    @PostConstruct
    public void createCategories() {
        final List<String> categoryNames = List.of(
                "computers and gaming",
                "phones",
                "vehicles",
                "clothing",
                "animals",
                "furniture"
        );

        for (String categoryName : categoryNames) {
            if (!adCategoryRepository.existsByCategoryName(categoryName)) {
                adCategoryRepository.save(new AdCategory(categoryName));
            }
        }
    }

    public List<AdCategory> getAllCategories() {
        return adCategoryRepository.findAll();
    }

    public AdCategory getCategoryById(Integer id) {
        return adCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public AdCategory createCategory(AdCategory adCategory) {
        return adCategoryRepository.save(adCategory);
    }

    public void deleteCategory(Integer id) {
        adCategoryRepository.deleteById(id);
    }
}
