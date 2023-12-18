package com.legant.Product.Repository;

import com.legant.Product.DTO.Range;
import com.legant.Product.Model.PriceRange;
import com.legant.Product.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();
    List<Product> findByCategoriesId(Long categoryId);

    List<Product> findAll(Specification<Product> spec);

    // List<Product> findAll(Specification<Product> spec, Pageable pageable);

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
}
