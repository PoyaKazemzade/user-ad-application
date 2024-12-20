package se.yrgo.adservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.yrgo.adservice.domain.AdCategory;
import se.yrgo.adservice.service.AdCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:5173")  // Allow requests from frontend URL
public class AdCategoryRestController {

    private final AdCategoryService adCategoryService;


    @Autowired
    public AdCategoryRestController(AdCategoryService adCategoryService) {
        this.adCategoryService = adCategoryService;
    }

    @GetMapping("/")
    public List<AdCategory> getAllCategories() {
        return adCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public AdCategory getCategoryById(@PathVariable Integer id) {
        return adCategoryService.getCategoryById(id);
    }

    @PostMapping("/")
    public AdCategory createCategory(@RequestBody AdCategory adCategory) {
        return adCategoryService.createCategory(adCategory);

    }

    @PutMapping("/{id}")
    public AdCategory updateCategory(@PathVariable Integer id, @RequestBody AdCategory adCategory) {
        adCategory.setId(id);
        return adCategoryService.createCategory(adCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        adCategoryService.deleteCategory(id);
    }
}
