package se.yrgo.adservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.adservice.data.AdCategoryRepository;
import se.yrgo.adservice.domain.AdCategory;

import java.util.List;

@Service
public class AdCategoryService {

    private final AdCategoryRepository adCategoryRepository;

    @Autowired
    public AdCategoryService(AdCategoryRepository adCategoryRepository) {
        this.adCategoryRepository = adCategoryRepository;
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
