package com.legant.Product.Service;

import com.legant.Product.DTO.AddProductRequest;
import com.legant.Product.DTO.NewProductsResponse;
import com.legant.Product.DTO.ProductRequest;
import com.legant.Product.DTO.ProductResponseForShop;
import com.legant.Product.Exception.FileStorageException;
import com.legant.Product.Helper.Misc;
import com.legant.Product.Model.Category;
import com.legant.Product.Model.Product;
import com.legant.Product.Repository.CategoryRepository;
import com.legant.Product.Repository.ProductRepository;
import com.legant.Product.Specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createProduct(AddProductRequest addProductRequest, List<String> categoryNames) throws Exception, FileStorageException {

        Set<Category> categories = new HashSet<>();

        for (String categoryName : categoryNames) {
            Category category = categoryRepository.findByCategory(categoryName);

            if (category != null) {
                categories.add(category);
            }

        }

        addProductRequest.setCategories(categories);
        productRepository.save(buildProduct(addProductRequest));
    }


    public List<NewProductsResponse> getNewProducts() {
        List<Product> products = productRepository.findAll();
        return Misc.productToNewProductsResponse(products);
    }

    public ProductResponseForShop searchProducts(ProductRequest productRequest) {

        Specification<Product> spec = Specification.where(null);

        if (productRequest.getPriceRanges() != null) {
            for (int i=0; i < productRequest.getPriceRanges().size(); i++) {
                spec = spec.or(ProductSpecification.priceBetween(
                        productRequest.getPriceRanges().get(i).getMinPrice(),
                        productRequest.getPriceRanges().get(i).getMaxPrice()
                ));
            }
        }

        if (productRequest.getCategoryId() != null) {
            spec = spec.and(ProductSpecification
                    .hasCategory(productRequest.getCategoryId()));
        }

        Pageable pageable = PageRequest.of(productRequest.getPage(), productRequest.getProductPerPage(), Sort.by("productPrice").ascending());

        if (productRequest.getSortBy() != null) {
            if (productRequest.getSortBy().equals("price")) {
                if (productRequest.isDescending()) {
                    pageable = PageRequest.of(productRequest.getPage(), productRequest.getProductPerPage(), Sort.by("productPrice").descending());
                } else {
                    pageable = PageRequest.of(productRequest.getPage(), productRequest.getProductPerPage(), Sort.by("productPrice").ascending());
                }
            }

            if (productRequest.getSortBy().equals("createdAt")) {
                if (productRequest.isDescending()) {
                    pageable = PageRequest.of(productRequest.getPage(), productRequest.getProductPerPage(), Sort.by("createdAt").descending());
                } else {
                    pageable = PageRequest.of(productRequest.getPage(), productRequest.getProductPerPage(), Sort.by("createdAt").ascending());
                }
            }
        }

        Page<Product> products =  productRepository.findAll(spec, pageable);

        return ProductResponseForShop.builder()
                .newProductsResponses(Misc.productToNewProductsResponse(products.getContent()))
                .next(products.hasNext())
                .build();
    }

    public NewProductsResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        assert product != null;
        return Misc.productToNewProductResponse(product);
    }


    private Product buildProduct(AddProductRequest addProductRequest) {

        return Product.builder()
                .categories(addProductRequest.getCategories())
                .productName(addProductRequest.getProductName())
                .sku(addProductRequest.getSku())
                .productPrice(addProductRequest.getProductPrice())
                .discount(addProductRequest.getDiscount())
                .fileName(addProductRequest.getFileName())
                .productRating(addProductRequest.getProductRating())
                .productDescription(addProductRequest.getProductDescription())
                .build();
    }




}
