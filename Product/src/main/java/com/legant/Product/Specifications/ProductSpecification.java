package com.legant.Product.Specifications;
import com.legant.Product.Model.Category;
import com.legant.Product.Model.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("productPrice"), minPrice, maxPrice);
    }

    public static Specification<Product> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, Category> categoryJoin = root.join("categories");
            return criteriaBuilder.equal(categoryJoin.get("id"), categoryId);
        };
    }
    public static Specification<Product> sortByPriceDesc() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("productPrice")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Product> sortByPriceAsc() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.asc(root.get("productPrice")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Product> sortByCreatedAtDesc() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("createdAt")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Product> sortByCreatedAtAsc() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.asc(root.get("createdAt")));
            return criteriaBuilder.conjunction();
        };
    }


}




