package com.legant.Product.Helper;

import com.legant.Product.DTO.NewProductsResponse;
import com.legant.Product.Model.Category;
import com.legant.Product.Model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Misc {

    @Value("${file.upload.path}")
    private static String fileUploadPath = ".\\src\\main\\resources\\images\\products";

    public static String saveImage(MultipartFile productImage) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + productImage.getOriginalFilename();
        System.out.println(uniqueFileName);
        Path filePath = Paths.get(fileUploadPath, uniqueFileName);
        System.out.println(filePath);
        Files.write(filePath, productImage.getBytes());
        return uniqueFileName;
    }

    public static List<NewProductsResponse> productToNewProductsResponse(List<Product> products) {
        return products.stream()
                .map(product -> {

                    NewProductsResponse newProductsResponse = NewProductsResponse.builder()
                            .productId(product.getProductId())
                            .productName(product.getProductName())
                            .productPrice(product.getProductPrice())
                            .productRating(product.getProductRating())
                            .discount(product.getDiscount())
                            .fileName(product.getFileName())
                            .categoryNames(product.getCategories().stream().map(Category::getCategory).collect(Collectors.toList()))
                            .build();

                    Path imagePath = Paths.get(fileUploadPath).resolve(product.getFileName());
                    try {
                        byte[] imageData = Files.readAllBytes(imagePath);
                        newProductsResponse.setProductImage(imageData);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return newProductsResponse;
                })
                .collect(Collectors.toList());
    }

    public static NewProductsResponse productToNewProductResponse(Product product) {

        NewProductsResponse newProductsResponse = NewProductsResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productRating(product.getProductRating())
                .discount(product.getDiscount())
                .fileName(product.getFileName())
                .categoryNames(product.getCategories().stream().map(Category::getCategory).collect(Collectors.toList()))
                .build();

        Path imagePath = Paths.get(fileUploadPath).resolve(product.getFileName());
        try {
            byte[] imageData = Files.readAllBytes(imagePath);
            newProductsResponse.setProductImage(imageData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newProductsResponse;
    }




}
