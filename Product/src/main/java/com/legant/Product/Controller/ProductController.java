package com.legant.Product.Controller;

import com.legant.Product.DTO.AddProductRequest;
import com.legant.Product.DTO.NewProductsResponse;
import com.legant.Product.DTO.ProductRequest;
import com.legant.Product.DTO.ProductResponseForShop;
import com.legant.Product.Exception.FileStorageException;
import com.legant.Product.Model.Product;
import com.legant.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.legant.Product.Helper.Misc;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/test")
    public String testController() {
        return "Product";
    }



    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addProduct(@ModelAttribute AddProductRequest productRequest,
                             @RequestParam("productImage") MultipartFile productImage,
                             @RequestParam("productCategories") List<String> categories) {
        try {
            String uniqueFileName = Misc.saveImage(productImage);
            productRequest.setFileName(uniqueFileName);

            productService.createProduct(productRequest, categories);

            return "Product added successfully!";
        } catch (IOException e) {
            return e.toString();
        } catch (FileStorageException | Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/all")
    public ProductResponseForShop searchAllProductsByFilters(@RequestBody ProductRequest productRequest) {
        return productService.searchProducts(productRequest);
    }

    @GetMapping("/new")
    public List<NewProductsResponse> getNewProducts() {
        return productService.getNewProducts();
    }

    @GetMapping("/product")
    public NewProductsResponse getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }
}
