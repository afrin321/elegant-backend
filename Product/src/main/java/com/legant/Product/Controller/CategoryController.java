package com.legant.Product.Controller;

import com.legant.Product.DTO.CategoryRequestResponse;
import com.legant.Product.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public String addCategory(@RequestBody CategoryRequestResponse category) {
        return categoryService.addCategory(category);
    }

    @GetMapping("/all")
    public List<CategoryRequestResponse> allCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/edit")
    public String updateCategory(@RequestBody CategoryRequestResponse category) {
        return categoryService.editCategory(category);
    }

}
