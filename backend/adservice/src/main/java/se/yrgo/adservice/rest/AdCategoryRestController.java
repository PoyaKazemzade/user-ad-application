package se.yrgo.adservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.yrgo.adservice.domain.AdCategory;
import se.yrgo.adservice.service.AdCategoryService;
import se.yrgo.adservice.service.CategoryMessageProducer;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class AdCategoryRestController {

    private final AdCategoryService adCategoryService;
    private final CategoryMessageProducer categoryMessageProducer;

    @Autowired
    public AdCategoryRestController(AdCategoryService adCategoryService, CategoryMessageProducer categoryMessageProducer) {
        this.adCategoryService = adCategoryService;
        this.categoryMessageProducer = categoryMessageProducer;
    }

    @GetMapping
    public List<AdCategory> getAllCategories() {
        return adCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public AdCategory getCategoryById(@PathVariable Integer id) {
        return adCategoryService.getCategoryById(id);
    }

    @PostMapping
    public AdCategory createCategory(@RequestBody AdCategory adCategory) {
        try {
            AdCategory savedCategory = adCategoryService.createCategory(adCategory);
            categoryMessageProducer.sendCategoryToQueue(savedCategory);
            return savedCategory;
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message to queue: " + e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public AdCategory updateCategory(@PathVariable Integer id, @RequestBody AdCategory adCategory) {
        adCategory.setId(id);
        return adCategoryService.createCategory(adCategory); // Using createCategory here for both update and create simplicity
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        adCategoryService.deleteCategory(id);
    }
}
