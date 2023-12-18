package com.legant.Product.Repository;

import com.legant.Product.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    Category findByCategory(String category);
}
