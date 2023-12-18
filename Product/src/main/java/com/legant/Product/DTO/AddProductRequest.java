package com.legant.Product.DTO;

import com.legant.Product.Model.Category;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddProductRequest {

    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal productPreviousPrice;
    private BigDecimal productRating;
    private String productDescription;
    private String productMeasurements;
    private String productColor;
    private boolean inWishlist;
    private int discount;
    private String sku;
    private String fileName;
    private String fileLocation;
    private Set<Category> categories = new HashSet<>();
    private Date createdAt;
    private Date updatedAt;
    private MultipartFile productImage;

}
