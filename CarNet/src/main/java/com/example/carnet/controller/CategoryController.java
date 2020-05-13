package com.example.carnet.controller;

import com.example.carnet.model.Category;
import com.example.carnet.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/carnet/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('category:read')")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('category:write')")
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('category:write')")
    public void updateCategory(@RequestBody Category category){ categoryService.updateCategory(category);}

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('category:write')")
    public void deleteCategoryById(@PathVariable("id") int id) {
        categoryService.deleteUserById(id);
    }
}
