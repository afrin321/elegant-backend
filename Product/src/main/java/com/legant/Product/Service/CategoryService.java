package com.legant.Product.Service;

import com.legant.Product.DTO.CategoryRequestResponse;
import com.legant.Product.Model.Category;
import com.legant.Product.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String addCategory(CategoryRequestResponse categoryRequestResponse) {

        Category categoryExists = categoryRepository.findByCategory(categoryRequestResponse.getCategory());

        if (categoryExists == null) {
            categoryRepository.save(
                    Category.builder()
                            .category(categoryRequestResponse.getCategory())
                            .build());
            return "Category added successfully";
        } else {
            return "Category already exists";
        }

    }

    public List<CategoryRequestResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> CategoryRequestResponse.builder()
                        .id(category.getId())
                        .category(category.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    public String editCategory(CategoryRequestResponse categoryRequestResponse) {
        Optional<Category> category = categoryRepository.findById(categoryRequestResponse.getId());

        if (category.isPresent()) {
            Category categoryForUpdate = category.get();
            categoryForUpdate.setCategory(categoryRequestResponse.getCategory());
            categoryRepository.save(categoryForUpdate);

            return "Category updated successfully";
        } else {
            return "Category does not exist";
        }
    }
}
