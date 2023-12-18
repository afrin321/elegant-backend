package com.legant.Product.DTO;

import com.legant.Product.Model.Category;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NewProductsResponse {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal productPreviousPrice;
    private BigDecimal productRating;
    private int discount;
    private String fileName;
    private byte[] productImage;
    private Set<Category> categories = new HashSet<>();
    private List<String> categoryNames = new ArrayList<String>();
    private boolean next;
    private Date createdAt;
}
